Laminar
-------
>Laminar, Airstream and Waypoint feature tests using Html5, W3.CSS, ScalaJs, uPickle and Scala 3.

Install
-------
>jsdom **must** be installed locally - ***not*** globally!
1. brew install node
2. npm install jsdom
3. instal Live Server extension via VSCode

Live Server
-----------
>Add the following Live Server settings to VSCode settings.json:
1. "liveServer.settings.port": 8080,
2. "liveServer.settings.root": "./target/scala-3.1.1/classes/"
3. "liveServer.settings.file": "index.html"
>which produces this root url: http://127.0.0.1:8080/
>See: https://github.com/ritwickdey/vscode-live-server/blob/master/docs/settings.md

Test
----
1. sbt clean test

Run
---
1. sbt clean compile fastLinkJS
2. click **Go Live** to autoload target/scala-3.x.x/classes/index.html into default browser
2. open **Developer Tools** to view details

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
1. Laminar - https://laminar.dev/
2. Waypoint - https://github.com/raquo/Waypoint
3. uPickle - https://github.com/com-lihaoyi/upickle