lazy val public = "public"

lazy val laminar = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "3.5.0-RC6",
    libraryDependencies ++= {
      lazy val jsoniterVersion = "2.30.7"
      Seq(
        "com.raquo" %%% "laminar" % "17.0.0",
        "com.raquo" %%% "waypoint" % "8.0.0",
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core" % jsoniterVersion,
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % jsoniterVersion % "compile-internal",
        "com.lihaoyi" %%% "ujson" % "4.0.0" % Test,
        "com.lihaoyi" %%% "utest" % "0.8.3" % Test
      )
    },
    scalacOptions ++= Seq(
      "-Wunused:all"
    ),
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / public,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
