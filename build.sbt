organization := "com.despegar"

name := "logcluster"

scalaVersion := "2.10.4"

version := "0.5"

//publishTo := Some("snapshots" at "http://nexus.despegar.it:8080/nexus/content/repositories/snapshots/")
publishTo := Some("releases" at "http://nexus.despegar.it:8080/nexus/content/repositories/releases/")
//publishTo := Some("snapshots" at "http://nexus:8080/nexus/content/repositories/snapshots-miami")

libraryDependencies ++= 
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2" ::
  "com.google.guava" % "guava" % "15.0" ::
  "joda-time" % "joda-time" % "2.3" ::
  // Required by guava
  "com.google.code.findbugs" % "jsr305" % "1.3.+" :: 
  Nil
 
scalacOptions ++= Seq("-deprecation", "-feature", "-language:reflectiveCalls")
