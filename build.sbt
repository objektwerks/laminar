lazy val public = "public"

lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "3.6.4-RC1",
    libraryDependencies ++= {
      lazy val jsoniterVersion = "2.33.0"
      Seq(
        "com.raquo" %%% "laminar" % "17.2.0",
        "com.raquo" %%% "waypoint" % "9.0.0",
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core" % jsoniterVersion,
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % jsoniterVersion % "compile-internal",
        "com.lihaoyi" %%% "ujson" % "4.1.0" % Test,
        "com.lihaoyi" %%% "utest" % "0.8.4" % Test
      )
    },
    scalacOptions ++= Seq(
      "-Wall"
    ),
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
