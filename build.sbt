name := "laminar"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.8"
enablePlugins(ScalaJSPlugin)
libraryDependencies ++= {
  Seq(
    "com.raquo" %%% "laminar" % "0.6"
  )
}