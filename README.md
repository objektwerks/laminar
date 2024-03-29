Laminar
-------
>Laminar, Airstream and Waypoint feature tests using Html5, W3.CSS, ScalaJs, Jsoniter, uJson, Snowpack and Scala 3.

Install
-------
1. brew install node
2. npm install jsdom ( must install **locally** )
3. npm install ( in project root directory )
>See **package.json** for installable dependencies.

Build
-----
1. npm install ( only when package.json changes )
2. sbt clean compile fastLinkJS
>See **target/public** directory.

Test
----
1. sbt clean test fastLinkJS

Dev
---
1. sbt ( new session )
2. ~ js/fastLinkJS
3. npx snowpack dev ( new session )
>Edits are reflected in the **fastLinkJS** and **snowpack** sessions.
>See **snowpack.config.json** and [Snowpack Config](https://www.snowpack.dev/reference/configuration) for configurable options.

Model
-----
* Page: IndexPage, RegisterPage, LoginPage, ItemsPage
* Serializer: Task, Page
* RegisterView
* IndexView
* LoginView
* TasksView 1 ---> 1 Renderer 1 ---> 1 Model 1 ---> * Task
* Store
* Router 1 ---> * Route 1 ---> 1 Page
* App

Resources
---------
* [Laminar](https://laminar.dev/)
* [Laminar Docs and Examples](https://laminar.dev/)
* [Waypoint](https://github.com/raquo/Waypoint)
* [Jsoniter](https://github.com/plokhotnyuk/jsoniter-scala)
* [uJson](https://www.lihaoyi.com/post/uJsonfastflexibleandintuitiveJSONforScala.html)
* [Snowpack](https://www.snowpack.dev/)
