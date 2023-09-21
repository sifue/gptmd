val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "gptmd",
    version := "1.0.0",

    scalaVersion := scala3Version,

    libraryDependencies += "io.circe" %% "circe-yaml" % "0.14.2",
    libraryDependencies += "io.circe" %% "circe-generic" % "0.14.2",
    libraryDependencies += "com.theokanning.openai-gpt3-java" % "api" % "0.16.0",
    libraryDependencies += "com.theokanning.openai-gpt3-java" % "client" % "0.16.0",
    libraryDependencies += "com.theokanning.openai-gpt3-java" % "service" % "0.16.0",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )

  ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "versions", "9", xs @ _*) => MergeStrategy.discard
  case "config.yml" => MergeStrategy.discard
  case "history.md" => MergeStrategy.discard
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}