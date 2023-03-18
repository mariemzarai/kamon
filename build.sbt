name := "HelloWorldServer"
version := "1.0"
scalaVersion := "2.12.15"
sbtVersion := "1.3.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.5",
  "com.typesafe.akka" %% "akka-stream" % "2.6.5",
  "com.typesafe.akka" %% "akka-http" % "10.1.11",
  "com.typesafe.play" %% "play-json" % "2.7.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.github.blemale" %% "scaffeine" % "3.1.0",
  "io.kamon" %% "kamon-core" % "2.1.0",
  "io.kamon" %% "kamon-status-page" % "2.1.0",
  "io.kamon" %% "kamon-akka-http" % "2.1.0",
  "io.kamon" %% "kamon-zipkin" % "2.1.0"
)

javaAgents += "io.kamon" % "kanela-agent" % "1.0.5"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, JavaAgent)
  .settings(
    fork in run := true
  )











//
//
//val akkaVersion = "2.6.18"
//val akkaHttpVersion = "10.2.7"
//val kamonVersion = "2.1.0"
//val kanelaAgentVersion = "1.0.5"
//lazy val root = (project in file("."))
////  .enablePlugins(JavaAppPackaging, JavaAgent)
//  .settings(
//    name := "HelloWorldServer",
//    scalaVersion := "2.12.15",
//    sbtVersion := "1.6.1"
//  )
//libraryDependencies ++= Seq(
//      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
//      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
//      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
//  "io.kamon" %% "kamon-bundle" % "2.5.9",
//  "io.kamon" %% "kamon-apm-reporter" % "2.5.9",
////      "io.kamon" %% "kamon-bundle" % kamonVersion,
////      "io.kamon" %% "kamon-apm-reporter" % kamonVersion,
////      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
////      "ch.qos.logback" % "logback-classic" % "1.2.3",
////      "com.github.blemale" %% "scaffeine" % "3.1.0",
////      "io.kamon" %% "kamon-core" % kamonVersion,
////      "io.kamon" %% "kamon-status-page" % kamonVersion,
////      "io.kamon" %% "kamon-akka-http" % kamonVersion,
////      "io.kamon" %% "kamon-zipkin" % kamonVersion
//    )

//javaAgents += "io.kamon" % "kanela-agent" % "1.0.5"


//javaOptions += s"-javaagent:${System.getProperty("user.home")}/.ivy2/cache/io.kamon/kanela-agent/jars/kanela-agent-$kanelaAgentVersion.jar"

















































































//lazy val root = (project in file("."))
//  .settings(
//    cinnamon in run := true, // Telemetry (Cinnamon)
//    cinnamon in test := true, // Telemetry (Cinnamon)
//    cinnamonLogLevel := "INFO", // Telemetry (Cinnamon)
//    name := "HelloWorldServer",
//    scalaVersion := "2.12.15",
//    sbtVersion := "1.6.1",
//
//  )
//enablePlugins(Cinnamon)
//
////resolvers := Seq(
////  "Maven" at "https://repo1.maven.org/"
////)
//libraryDependencies += Cinnamon.library.cinnamonOpenTracingJaeger // Telemetry (Cinnamon)
//
//libraryDependencies += Cinnamon.library.cinnamonCHMetrics
//// Telemetry (Cinnamon)
//libraryDependencies += Cinnamon.library.cinnamonAkka
//// Telemetry (Cinnamon)
//libraryDependencies += Cinnamon.library.cinnamonAkkaHttp
////libraryDependencies += Cinnamon.library.cinnamonOpenTracingZipkin
////libraryDependencies += "com.typesafe.cinnamon" %% "cinnamon-opentracing-zipkin" % "2.16.1"
//
//libraryDependencies += "io.kamon" %% "kamon-akka" % "2.5.9"
//libraryDependencies += Cinnamon.library.cinnamonOpenTracingZipkin
//libraryDependencies += Cinnamon.library.cinnamonAkkaHttp
//libraryDependencies += Cinnamon.library.cinnamonDataDog //For using DataDog as backend agent
//
//// Telemetry (Cinnamon)
//
//val akkaActorsVersion = "2.6.16"
//val akkaStreamsVersion = "2.6.16"
//val akkaHttpVersion = "10.2.7"
//libraryDependencies ++= Seq(
//  Cinnamon.library.cinnamonOpenTracingZipkin,
//
//  "com.typesafe.akka" %% "akka-actor" % akkaActorsVersion,
//  "com.typesafe.akka" %% "akka-stream" % akkaStreamsVersion,
//  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
//  //        " io.zipkin.java" % "zipkin" % "2.16.1"
//
//
//)
//resolvers in ThisBuild += "lightbend-commercial-mvn" at "https://repo.lightbend.com/pass/5Ua3UKTVZlRXy_dFJnXKnSxhAUEJpRfofU7LUsT47kF-zX1U/commercial-releases"
//resolvers in ThisBuild += Resolver.url("lightbend-commercial-ivy",
//  url("https://repo.lightbend.com/pass/5Ua3UKTVZlRXy_dFJnXKnSxhAUEJpRfofU7LUsT47kF-zX1U/commercial-releases"))(Resolver.ivyStylePatterns)
