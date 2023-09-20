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

    println(history)
  
    val messages = Seq(ChatMessage("user", "こんにちは、ジョークを一つ教えてください。"))
    request.setMessages(messages.asJava)

    service.createChatCompletion(request)
    .getChoices()
    .forEach(choice => {
      println(choice.getMessage().getContent())
    })

  def shutdown(): Unit =
    service.shutdownExecutor()
