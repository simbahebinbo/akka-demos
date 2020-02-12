name := """play2-sample"""
organization := "com.lab"

version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

//指定java版本
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies += guice
