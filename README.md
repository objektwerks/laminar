Laminar
-------
>Laminar, Airstream and Waypoint feature tests using Html5, W3.CSS, ScalaJs, uPickle and Scala 3.

Install
-------
>jsdom **must** be installed locally - ***not*** globally!
1. brew install node
2. npm install jsdom ( must install **locally** )
3. npm install ( in project root directory )
>See **package.json** for installable dependencies.

Build
-----
1. npm install ( only when package.json changes )
2. sbt clean compile fastLinkJS
>See **js/target/public** directory.

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