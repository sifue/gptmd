## ChatGPT Markdown Commandline Tool
マークダウン形式のファイルをインプットとして、そのマークダウンにChatGPTの続きを出力させるコマンドラインツール。

### 設定ファイル

- config.yml
- history.md

以上を用意してください。

#### config.ymlの仕様

```yaml
openai_api_key : sk-99999999999999999999999999999999999999
history_file: ./history.md
chatgpt_config:
  model: gpt-3.5-turbo # gpt-3.5-turbo or gpt-4
  max_tokens: 1000
  temperature: 1
  top_p: 1
```

### history.mdの仕様

- `<!-- gptmd-user-begin --> <!-- gptmd-user-end -->` で囲まれた部分は、ユーザー発言
- `<!-— gptmd-assistant-begin -—> <!-— gptmd-assistant-end -—>` で囲まれた部分は、アシスタント発言
- `<!-- gptmd-system-begin --> <!-- gptmd-system-end -->` で囲まれた部分は、システム発言

#### history.mdのサンプル
```markdown
<!-- gptmd-system-begin -->
You are ChatGPT, a large language model trained by OpenAI.
Carefully heed the user's instructions.
Respond using Markdown in Japanese.
<!-- gptmd-system-end -->
<!-- gptmd-user-begin -->
こんにちは、ジョークを一つ教えてください。
<!-- gptmd-user-end -->
```

### 実行方法
OpenJDK 11.0.2以上をインストールしておく。
sbt 1.9.4以上をインストールしておく。
Scalaのセットアップは、[Getting Started | Scala Documentation](https://docs.scala-lang.org/getting-started/index.html)を参照のこと。

```
sbt run
```

### über jarのビルド方法、実行方法
実行可能なüber jarをビルドする方法は、

```
sbt assembly
java -jar target/scala-3.3.1/gptmd-assembly-1.0.0.jar
```

### 利用ライブラリ

- [circe-yaml](https://github.com/circe/circe-yaml)
- [openai-java](https://github.com/TheoKanning/openai-java)