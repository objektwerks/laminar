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
>which produces this url: http://127.0.0.1:8080/

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
* Page: LoginPage, ItemsPage
* Serializer: Item, Page
* LoginView
* ItemsView 1 ---> 1 Renderer 1 ---> 1 Model 1 ---> * Item
* Store
* Router 1 ---> * Route 1 ---> 1 LoginPage, ItemsPage
* App