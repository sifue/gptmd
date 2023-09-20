## ChatGPT Markdown Commandline Tool
マークダウン形式のファイルをインプットとして、そのマークダウンにChatGPTの続きを出力させるコマンドラインツール。

### ビルド方法
OpenJDK 11.0.2以上をインストールしておく。
sbt 1.9.4以上をインストールしておく。
Scalaのセットアップは、[Getting Started | Scala Documentation](https://docs.scala-lang.org/getting-started/index.html)を参照のこと。

```
sbt compile
sbt run
```

### history.mdの仕様

-  `<!-- gptmd-user-begin --> <!-- gptmd-user-end -->` で囲まれた部分は、ユーザー発言
- `<!-— gptmd-assistant-begin -—> <!-— gptmd-assistant-end -—>` で囲まれた部分は、アシスタント発言
- `<!-- gptmd-system-begin --> <!-- gptmd-system-end -->` で囲まれた部分は、システム発言


#### サンプル
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


### 利用ライブラリ

- [circe-yaml](https://github.com/circe/circe-yaml)
- [openai-java](https://github.com/TheoKanning/openai-java)