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
