lazy val public = "public"

lazy val laminar = (project in file("."))3.7.2-RC1
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "laminar",
    organization := "objektwerks",
    version := "0.1-SNAPSHOT",
    scalaVersion := "3.7.2-RC1",
    libraryDependencies ++= {
      lazy val jsoniterVersion = "2.36.7"
      Seq(
        "com.raquo" %%% "laminar" % "17.2.1",
        "com.raquo" %%% "waypoint" % "8.0.1", // Don't upgrade due to breaking changes!
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core" % jsoniterVersion,
        "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % jsoniterVersion % "compile-internal",
        "com.lihaoyi" %%% "ujson" % "4.2.1" % Test,
        "com.lihaoyi" %%% "utest" % "0.8.5" % Test
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
