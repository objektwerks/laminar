Laminar
-------
>Laminar, Airstream and Waypoint feature tests using ScalaJs and Scala 3.

Install
-------
>jsdom **must** be installed locally - **not** globally!
1. brew install node
2. npm install jsdom

Test
----
1. sbt clean test

Run
---
1. sbt clean compile fastLinkJS
2. Select target/scala-3.1.0/classes/index.html
3. Open with Intellij or Live Server
4. Select newly opened browser tab to view index.html
5. Open Developer Tools

Model
-----
* Page: LoginPage, ItemsPage
* Serializer: Item, Page
* LoginView
* ItemsView 1 ---> 1 Renderer 1 ---> 1 Model 1 ---> * Item
* Router 1 ---> * Route 1 ---> 1 LoginPage, ItemsPage
* App