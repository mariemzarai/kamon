//addSbtPlugin("com.lightbend.cinnamon" % "sbt-cinnamon" % "2.17.2")

//credentials += Credentials(Path.userHome / ".lightbend" / "commercial.credentials") // Specify the path of the file created in step 1 above which contains bintray credentials
//
//resolvers += Resolver.url("lightbend-commercial",
//  url("https://repo.lightbend.com/commercial-releases"))(Resolver.ivyStylePatterns)






addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.7.1")
addSbtPlugin("io.kamon" % "sbt-kanela-runner" % "2.0.6")
addSbtPlugin("com.lightbend.sbt" % "sbt-javaagent" % "0.1.5")