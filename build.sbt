organization := "com.despegar"

name := "logcluster"

scalaVersion := "2.11.6"

version := "0.6.1"

publishTo := Some("releases" at "http://nexus.despegar.it:8080/nexus/content/repositories/releases/")

libraryDependencies ++= 
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0" ::
  "com.google.guava" % "guava" % "18.0" ::
  "joda-time" % "joda-time" % "2.4" ::
  // Required by guava
  "com.google.code.findbugs" % "jsr305" % "1.3.+" :: 
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.8" ::
  Nil
 
scalacOptions ++= Seq("-deprecation", "-feature", "-language:reflectiveCalls")
