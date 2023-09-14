package org.soichiro.gptmd
import org.soichiro.gptmd.ConfigLoader

@main def main: Unit =
  val configLoader = ConfigLoader()
  val config = configLoader.load()
  val service = ChatGPTService(config)
  service.chat()

  service.shutdown()
  println("finished.")
