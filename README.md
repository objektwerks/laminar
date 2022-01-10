Laminar
-------
>Laminar, Airstream and Waypoint feature tests using ScalaJs and Scala 3.

Install
-------
>jsdom **must** be installed locally - **not** globally!
1. brew install node
2. npm install jsdom

Live Server
-----------
>I added the following properties to settings.json:
1. "liveServer.settings.port": 8080,
2. "liveServer.settings.root": "./target/scala-3.1.0/classes/"
3. "liveServer.settings.file": "index.html"
>which produces this url: http://127.0.0.1:8080/
>See: https://github.com/ritwickdey/vscode-live-server/blob/master/docs/settings.md

Test
----
1. sbt clean test

Run
---
1. sbt clean compile fastLinkJS
2. click **Go Live** to autoload target/scala-3.1.0/classes/index.html into default browser
2. open **Developer Tools** to view details

Model
-----
* Page: IndexPage, LoginPage, ItemsPage
* Serializer: Task, Page
* IndexView
* LoginView
* TasksView 1 ---> 1 Renderer 1 ---> 1 Model 1 ---> * Task
* Store
* Router 1 ---> * Route 1 ---> 1 Page
* App