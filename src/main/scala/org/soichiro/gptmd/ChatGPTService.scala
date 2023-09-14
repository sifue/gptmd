package org.soichiro.gptmd


class ChatGPTService(
    config: Config
):



  def chat(): Unit =
    service.listModels.map(models => models.foreach(println))

    service.retrieveModel(ModelId.gpt_3_5_turbo).flatMap { model =>
      val prompt = "こんにちは、ジョークを一つ教えてください。"
      val setting = CreateCompletionSettings(
        model = config.chatgpt_config.model,
        max_tokens = Some(config.chatgpt_config.max_tokens),
        temperature = Some(config.chatgpt_config.temperature),
        top_p = Some(config.chatgpt_config.top_p)
      )

      service
        .createCompletion(
          prompt = prompt,
          settings = setting
        )
        .map(completion => {
          println(completion)
          println(completion.choices.head.text)
        })
    }

  def shutdown(): Unit =
    service.close()
