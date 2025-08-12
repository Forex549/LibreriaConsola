# 📚 Librería en Consola

Aplicación Java de línea de comandos que permite **buscar libros por título** desde la API pública **Gutendex**, almacenar la información en una base de datos y gestionar autores y libros de forma sencilla.  

Las propiedades del programa se encuentran en el archivo `application.properties`. Desde ahí configuramos los valores de la conexión a la base de datos.  
En este caso, por seguridad, se usan variables de entorno para asignar valores a:  
- `DB_HOST`  
- `DB_NAME`  
- `DB_USER`  
- `DB_PASSWORD`
  
Sin embargo se pueden colocar los valores directamente en el archivo y de igual forma funcionará.
El resto de configuraciones indican que:  
- La base de datos se actualiza automáticamente con los cambios que se hagan en el programa.  
- Hibernate mostrará en consola todas las consultas SQL que ejecuta.  

Antes de iniciar el programa se debe **crear la base de datos** en el gestor que se esté usando, ya que **JPA no crea la base de datos automáticamente**, aunque sí crea las tablas si estas no existen.  

Se usó **JPQL** (Java Persistence Query Language) en las interfaces de repositorio, por lo que además de PostgreSQL también se puede utilizar **MySQL**, **SQL Server** u otros gestores de bases de datos relacionales compatibles con JPA.  

El programa utiliza **DTO** para mapear la respuesta de la API y posteriormente crear objetos en memoria, que se guardan en la base de datos usando métodos definidos en las interfaces del paquete `Repository`.  

---

##  Características

- 🔍 **Búsqueda de libros por título** usando la API Gutendex.  
- 📖 Obtención de datos: título, autor, año de nacimiento/muerte del autor, géneros, temas, idioma y cantidad de descargas.  
- 💾 **Guardado automático** en base de datos evitando duplicados en cada búsqueda.  
- 🌐 **Filtro por idioma** para listar libros según su idioma.  
- 👨‍💻 **Listado de autores** almacenados en la base de datos.  
- ✍ **Registro automático de autores** al guardar libros.  
- ⚡ **Ejecución en consola**: rápida, ligera y sin dependencias gráficas.  

---

##  Tecnologías utilizadas

- ☕ **Java 17+**  
- 🌱 **Spring Boot**  
- 🐘 **PostgreSQL**  
- 📡 **API Gutendex**  

---
