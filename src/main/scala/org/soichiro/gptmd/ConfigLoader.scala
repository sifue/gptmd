package org.soichiro.gptmd

import scala.io.Source
import scala.util.Using

import cats.syntax.either._
import io.circe._
import io.circe.generic.auto._
import io.circe.yaml.parser

case class ChatGptConfig(model: String, max_tokens: Int, temperature: Double, top_p: Double, timeout: Int)
case class Config(openai_api_key: String, history_file: String, chatgpt_config: ChatGptConfig)

class ConfigLoader:

  def load(): Config =
    Using(Source.fromFile("config.yml")) { source =>
      val json = parser.parse(source.mkString)
      json
      .leftMap(err => err: Error)
      .flatMap(_.as[Config])
      .valueOr(throw _)
    }.getOrElse(throw new Exception("config.yml is not found"))
    
    