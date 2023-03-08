lazy val public = "public"

lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "3.2.2",
    libraryDependencies ++= {
      Seq(
        "com.raquo" %%% "laminar" % "15.0.0-M7",
        "com.raquo" %%% "waypoint" % "6.0.0-M5",
        "com.lihaoyi" %%% "upickle" % "3.0.0-M2",
        "com.lihaoyi" %%% "utest" % "0.8.1" % Test
      )
    },
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
