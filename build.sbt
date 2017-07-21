name := "my-first-app"

version := "1.0.0-SNAPSHOT"

libraryDependencies ++= Seq(
  cache,
  ws,
  
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1",
  "com.h2database" % "h2" % "1.4.191"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

routesGenerator := InjectedRoutesGenerator
