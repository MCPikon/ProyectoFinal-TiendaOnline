# Proyecto Final (Tienda Online)

Proyecto final del grado superior (DAW) que consiste en una p�gina web 
de compra/venta de videojuegos creada con Java + Spring para backend y 
HTML + Mustache (para plantillas), CSS + Bootstrap, y JavaScript con JQuery y JSLT 
para la parte frontend.

## Tecnolog�as
* Java 8
* Spring Framework
* Hibernate
* JQuery 3.6.0+
* JSCookie
* JSLT
* Bootstrap 5.+
* Mustache
* MySQL Database + Conector Java

## C�mo funciona

Este proyecto utliza una p�gina web para comunicarse con el usuario, 
y por debajo hace las operaciones con la base de datos.
El proyecto esta organizado en 2 partes: parte servidor y cliente.

En la parte servidor usa Java + Spring Framework, todo organizado en diferentes
paquetes utilizando el sistema REST, con sus diferentes servicios con sus m�todos
definidos y sus implementaciones en otros archivos; se comunica con la base de 
datos con la ayuda de Hibernate usando sessionFactory.

En la parte cliente usa HTML, CSS (Mayoritariamente Bootstrap 5) y JavaScript 
(JQuery para cargar elementos din�micamente en las jsp, JSCookie para el uso de 
las cookies en la parte p�blica y de admin, y, Mustache para el uso de plantillas para
las jsp tanto para espa�ol, como para ingl�s).