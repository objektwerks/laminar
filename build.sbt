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
        "com.lihaoyi" %%% "ujson" % "1.4.3",
        "com.lihaoyi" %%% "utest" % "0.7.10" % Test
      )
    },
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")    
  )