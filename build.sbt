name := "ZIO-Kafka-App"
version := "1.0"
scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "1.0.0-RC20",
  "dev.zio" %% "zio-streams" % "1.0.0-RC20",
  "dev.zio" %% "zio-kafka"   % "0.9.0"
)

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
