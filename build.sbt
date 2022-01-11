import bloop.shaded.com.google.common.io.Resources
lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "3.1.0",
    libraryDependencies ++= {
      Seq(
        "com.raquo" %%% "laminar" % "0.14.2",
        "com.raquo" %%% "waypoint" % "0.5.0",
        "com.lihaoyi" %%% "upickle" % "1.4.3",
        "com.lihaoyi" %%% "ujson" % "1.4.3",
        "com.lihaoyi" %% "cask" % "0.8.0",
        "com.lihaoyi" %%% "utest" % "0.7.10" % Test
      )
    },
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / "scala-3.1.0" / "classes" / "js",
    Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / "scala-3.1.0" / "classes" / "js",
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")    
  )