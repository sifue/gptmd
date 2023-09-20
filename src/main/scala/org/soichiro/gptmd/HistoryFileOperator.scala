package org.soichiro.gptmd

import scala.io.Source
import java.io.{File, FileWriter, BufferedWriter}

val messageRegex = raw"(?s)<!-- gptmd-(user|assistant|system)-begin -->(.*?)<!-- gptmd-(user|assistant|system)-end -->".r
val userMessageRegex = raw"(?s)<!-- gptmd-user-begin -->(.*?)<!-- gptmd-user-end -->".r
val assistantMessageRegex = raw"(?s)<!-- gptmd-assistant-begin -->(.*?)<!-- gptmd-assistant-end -->".r
val systemMessageRegex = raw"(?s)<!-- gptmd-system-begin -->(.*?)<!-- gptmd-system-end -->".r

case class Message(role: String, content: String)

class HistoryFileOperator(val historyFilePath: String):

  def loadHistory(): Seq[Message] =

    val source = Source.fromFile(historyFilePath)
    // 大きいが一気に正規表現で処理するためメモリに載せる
    val text = source.getLines().toList.mkString("\n") 
    source.close()  
    val matches: Iterator[String] = messageRegex.findAllIn(text)
    
    matches.toList.map(message => {
      message match {
        case userMessageRegex(message) => Message("user", message)
        case assistantMessageRegex(message) => Message("assistant", message)
        case systemMessageRegex(message) => Message("system", message)
        case text: String => throw new Exception(s"Cant't parse exeption: ${text}")
      }
    })

  def append(message: Message) : Unit =
    val file = File(historyFilePath)
    val bw = BufferedWriter(FileWriter(file, true))

    try 
      message match
        case Message("user", content) => 
          bw.write(s"\n<!-- gptmd-user-begin -->\n${content}\n<!-- gptmd-user-end -->\n")
        case Message("assistant", content) => 
          bw.write(s"\n<!-- gptmd-assistant-begin -->\n${content}\n<!-- gptmd-assistant-end -->\n")    
        case Message("system", content) => 
          bw.write(s"\n<!-- gptmd-system-begin -->\n${content}\n<!-- gptmd-system-end -->\n")
        case _ => throw new Exception(s"Unknown role: ${message.role}") 
    finally bw.close()      
