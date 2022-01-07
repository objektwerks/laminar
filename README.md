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
2. Open: 1) src/main/resources/index.html or 2) target/scala-3.1.0/classes/index.html
3. Select: 1) brower option from editor popup or 2) Open with Live Server
4. Select newly opened browser tab to see index.html.
5. Open Developer Tools to see browser console and the like.

Model
-----
* Page: LoginPage, ItemsPage
* Serializer: Item, ItemsPage, Page
* LoginView
* ItemsView 1 ---> 1 Renderer 1 ---> 1 Model 1 ---> * Item
* Router 1 ---> * Route 1 ---> 1 LoginPage, ItemsPage
* App