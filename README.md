# Proyecto Final (Tienda Online)

Proyecto final del grado superior (DAW) que consiste en una página web 
de compra/venta de videojuegos creada con Java + Spring para backend y 
HTML + Mustache (para plantillas), CSS + Bootstrap, y JavaScript con JQuery y JSLT 
para la parte frontend.

## Tecnologías
* Java 8
* Spring Framework
* Hibernate
* JQuery 3.6.0+
* JSCookie
* JSLT
* Bootstrap 5.+
* Mustache
* MySQL Database + Conector Java

## Cómo funciona

Este proyecto utliza una página web para comunicarse con el usuario, 
y por debajo hace las operaciones con la base de datos.
El proyecto esta organizado en 2 partes: parte servidor y cliente.

En la parte servidor usa Java + Spring Framework, todo organizado en diferentes
paquetes utilizando el sistema REST, con sus diferentes servicios con sus métodos
definidos y sus implementaciones en otros archivos; se comunica con la base de 
datos con la ayuda de Hibernate usando sessionFactory.

En la parte cliente usa HTML, CSS (Mayoritariamente Bootstrap 5) y JavaScript 
(JQuery para cargar elementos dinámicamente en las jsp, JSCookie para el uso de 
las cookies en la parte pública y de admin, y, Mustache para el uso de plantillas para
las jsp tanto para español, como para inglés).