Laminar
-------
>Laminar feature tests.

Items
-----
>Items is a Html component that composes:

1. Items 1 ---> 1 View 1 ---> 1 Model 1 ---> * Item
2. Items 1 ---> 1 Logger

Test
----
1. sbt clean test

Run
---
1. sbt clean compile fastLinkJS
2. Open src/main/resources/index.html.
3. Select brower option from editor popup.
4. Select newly opened tab to see index.html.
5. Open Developer Tools to see browser console.