lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.13.3",
    libraryDependencies ++= {
      Seq(
        "com.raquo" %%% "laminar" % "0.10.3"
      )
    }
  )