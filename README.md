# Proyecto Final (Tienda Online)

[![Java](https://img.shields.io/badge/Java-8%2B-saddlebrown?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=101010)](https://docs.oracle.com/en/java/index.html) &nbsp;
[![Spring Framework](https://img.shields.io/badge/Spring%20Framework-3.1.3+-%236DB33F?style=for-the-badge&logo=spring&logoColor=white&labelColor=101010)](https://spring.io/projects/spring-framework) &nbsp;
[![Hibernate](https://img.shields.io/badge/Hibernate-4%2B-%2359666C?style=for-the-badge&logo=hibernate&logoColor=white&labelColor=101010)](https://hibernate.org/) &nbsp;
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5%2B-%237952B3?style=for-the-badge&logo=bootstrap&logoColor=white&labelColor=101010)](https://getbootstrap.com/) &nbsp;
[![MySQL](https://img.shields.io/badge/MySQL-8.0-%234479A1?style=for-the-badge&logo=mysql&logoColor=white&labelColor=101010)](https://www.mysql.com/)

## Autor

* [Javier Picón](https://github.com/MCPikon)

## Descripción

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

## Licencia

Este proyecto es de código abierto y está disponible bajo la [Licencia 2.0 de Apache](LICENSE).
