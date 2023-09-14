package org.soichiro.gptmd

import com.theokanning.openai.service.OpenAiService
import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage

import collection.JavaConverters.seqAsJavaListConverter

class ChatGPTService(config: Config):
  val service = new OpenAiService(config.openai_api_key)
  def chat(): Unit =

    val chatCompletionRequest = new ChatCompletionRequest();
    chatCompletionRequest.setModel(config.chatgpt_config.model)
    val messages = Seq(ChatMessage("user", "こんにちは、ジョークを一つ教えてください。"))
    chatCompletionRequest.setMessages(messages.asJava)

    service.createChatCompletion(chatCompletionRequest).getChoices().forEach(choice => {
      println(choice.getMessage().getContent())
    })

  def shutdown(): Unit =
    service.shutdownExecutor()
