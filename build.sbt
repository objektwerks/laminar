lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.13.7",
    libraryDependencies ++= {
      Seq(
        "com.raquo" %%% "laminar" % "0.13.1",
        "com.outr" %%% "scribe" % "3.6.3",
        "com.lihaoyi" %%% "utest" % "0.7.10" % Test
      )
    },
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
