Laminar
-------
>Laminar feature tests.

Items
-----
>Items is an Html component that composes an ItemsModel and ItemsView used to render an HtmlElement.

1. Items 1 ---> 1 ItemsModel
2. Items 1 ---> 1 ItemsView 1 ---> ItemsModel

Run
---
1. sbt clean compile fastOptJS
2. Open index.html in browser via Intellij open html file in target browser feature.
3. In your browser tab you will see a url like this: http://localhost:63342/laminar/index.html?_ijt=107dmvtt4ph53181nmhcp18d4i