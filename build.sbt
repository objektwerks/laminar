lazy val public = "public"

lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "3.2.0-RC3"
    libraryDependencies ++= {
      Seq(
        "com.raquo" %%% "laminar" % "0.14.2",
        "com.raquo" %%% "waypoint" % "0.5.0",
        "com.lihaoyi" %%% "upickle" % "2.0.0",
        "com.lihaoyi" %%% "utest" % "0.8.0" % Test
      )
    },
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
