


# Backend Diccionario de Lenguas de Señas

API REST para el backend del proyecto "Diccionario de Lenguas de Señas".  
Permite gestionar signos, usuarios y autenticación para la aplicación frontend.

---

## Tecnologías

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Mysql

- JPA / Hibernate
- Maven

---

## Funcionalidades principales

- CRUD de signos de lenguas de señas
- Autenticación y autorización con JWT
- Gestión de usuarios y roles
- Búsqueda avanzada de signos
- API REST segura y escalable

---

## Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/Nadharia/backend-diccionario.git
cd backend-diccionario
````

2. Configura la base de datos Mysql y crea la base para el proyecto.

3. Crea un archivo `application.properties` o `application.yml` en `src/main/resources` con la configuración de conexión a tu base de datos, por ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/diccionario
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Construye y ejecuta la aplicación:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## Uso

* La API correrá en `http://localhost:8080` (por defecto).
* Accede a los endpoints para gestionar signos, usuarios y autenticación.
* Usa un cliente REST (Postman, Insomnia) o conecta con el frontend.

---

## Endpoints principales

* `POST /auth/login` - Iniciar sesión y obtener token JWT
* `POST /auth/register` - Registrar nuevo usuario
* `GET /signos` - Listar signos
* `POST /signo` - Crear nuevo signo
* `PUT /signo/{id}` - Actualizar signo
* `DELETE /signo/{id}` - Eliminar signo

---

## Contribuciones

¡Contribuciones bienvenidas! Por favor abre un issue o pull request para sugerir mejoras o nuevas funcionalidades.

---

## Licencia

Este proyecto está bajo la licencia MIT.
Consulta el archivo LICENSE para más detalles.

---

## Contacto

Joaco Romero – [linkedin.com/in/joaco](https://linkedin.com/in/joaco) – [joaco@email.com](mailto:joaco@email.com)





