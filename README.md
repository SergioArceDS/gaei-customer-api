# Proyecto GAEI-CUSTOMER

API REST desarrollada con Java 21 y Spring Boot como parte de una prueba técnica para Grupo CINTE.

## Tecnologías
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Lombok
- Spring Validation
- Maven
- Base de datos H2

##  Configuración
Las propiedades de configuración están **externalizadas** y no se incluyen en el repositorio por seguridad.

Estructura de ejemplo de archivo properties para dev:
```properties
spring.application.name=gaei-customer
server.port=9090

# H2
spring.datasource.url=jdbc:h2:mem:gaei_dev
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Logs
logging.level.root=INFO
logging.level.com.gaei.customer=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

logging.file.name=${LOG_PATH:C:/gaei/logs}/gaei-dev.log