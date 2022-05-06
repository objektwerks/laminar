libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.9.0") // upgrading to 1.10.0 breaks js IR build!
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.3.1")
