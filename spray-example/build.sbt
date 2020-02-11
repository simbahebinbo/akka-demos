organization := "com.lab"

version := "1.0.0"

scalaVersion := "2.11.0"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.5.29"
  val sprayV = "1.3.4"
  val specs2V = "4.8.3"
  val json4sV = "3.6.7"

  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-json" % sprayV,
    "org.json4s" %% "json4s-native" % json4sV,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "io.spray" %% "spray-testkit" % sprayV % Test,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % Test,
    "org.specs2" %% "specs2-core" % specs2V % Test
  )
}

val mClass = "com.lab.Boot"
mainClass in(Compile, packageBin) := Some(mClass)
mainClass in(Compile, run) := Some(mClass)

Revolver.settings
