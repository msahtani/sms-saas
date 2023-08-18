# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/#build-image)
* [Spring Web Services](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#io.webservices)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#data.nosql.mongodb)
* [Java Mail Sender](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#io.email)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#features.docker-compose)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#data.sql.jpa-and-spring-data)
* [Spring Session](https://docs.spring.io/spring-session/reference/)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#actuator)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#data.sql.jdbc)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#messaging.amqp)
* [WebSocket](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#messaging.websockets)
* [OAuth2 Client](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#web.security.oauth2.client)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsinge/index.html#appendix.configuration-metadata.annotation-processor)

### Guides

The following guides illustrate how to use some features concretely:

* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* mongodb: [`mongo:latest`](https://hub.docker.com/_/mongo)
* mysql: [`mysql:latest`](https://hub.docker.com/_/mysql)
* postgres: [`postgres:latest`](https://hub.docker.com/_/postgres)
* rabbitmq: [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)

Please review the tags of the used images and set them to the same as you're running in production.

