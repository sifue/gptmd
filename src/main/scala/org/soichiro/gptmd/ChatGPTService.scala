package org.soichiro.gptmd

import com.theokanning.openai.service.OpenAiService
import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage

import collection.JavaConverters.seqAsJavaListConverter

class ChatGPTService(config: Config):
  val service = new OpenAiService(config.openai_api_key)
  def chat(): Unit =

    val request = new ChatCompletionRequest()
    request.setModel(config.chatgpt_config.model)
    request.setMaxTokens(config.chatgpt_config.max_tokens)
    request.setTemperature(config.chatgpt_config.temperature)
    request.setTopP(config.chatgpt_config.top_p)

    val historyFileOperator = HistoryFileOperator(config.history_file)
    val history = historyFileOperator.loadHistory()

    val chatMessages = 
      history.map(message => ChatMessage(message.role, message.content))

    request.setMessages(chatMessages.asJava)

    chatMessages.foreach(chatMessage => {
      println(s"${chatMessage.getRole()}:\n${chatMessage.getContent()}")
    } )

    println("================ 以上をリクエストで送信 ================")

    val responseChoices = service.createChatCompletion(request).getChoices()
    val responseChatMessage = responseChoices.get(0).getMessage()
    val newMessage = Message(responseChatMessage.getRole(), responseChatMessage.getContent())

    println(s"${newMessage.role}:\n${newMessage.content}")
    historyFileOperator.append(newMessage)

  def shutdown(): Unit =
    service.shutdownExecutor()
