## ChatGPT Markdown Commandline Tool

(開発中)

マークダウン形式のファイルをインプットとして、そのマークダウンにChatGPTの続きを出力させるコマンドラインツールになる予定。

現在、設定からモデル名を読み込みChatGPTのAPIにジョークを言わせるところまでの実装が完了。

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


### 利用ライブラリ

- [circe-yaml](https://github.com/circe/circe-yaml)
- [openai-java](https://github.com/TheoKanning/openai-java)