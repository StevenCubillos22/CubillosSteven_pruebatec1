# Prueba técnica CRUD en Java

En está prueba técnica, se ha realizado un CRUD en Java que se manejará a través de la consola la administración  de los empleados

## Creación de BBDD y proyecto


En el administrador de BBDD (en mi caso PHPMyadmin), cree la base de datos de empleados con el siguiente comando

```http
  CREATE DATABASE empleados;
```

y se nos crearía con éxito:

![Captura de pantalla 2024-12-09 200716](https://github.com/user-attachments/assets/d3b1a010-db2e-4496-9931-9fbeb9eecf83)


## Creación del proyecto

En mi caso usé Intellij ya que el IDE es bueno para crear programas en Java, pero sobre todo ofrece conexión de BBDD agregando una dependencia que vamos a usar.

Para crear el proyecto, es necesario configurarlo con Maven porque nos ayudará con la gestión de dependencias y la organización del proyecto.

![Captura de pantalla 2024-12-10 193855](https://github.com/user-attachments/assets/fb099840-1941-4c9f-9824-8fa40d158005)

## Agregar dependencias

Para asegurar la conexion con la BBDD, primero que todo, vamos a nuestro archivo pom.xml para agregar la dependencia de MySQL dentro la la misma de dependencias


```http
<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
</dependency>
```
Asi como las de JPA:

```http
        <dependency>
            <groupId>org.eclipse.persistence</groupId>
            <artifactId>org.eclipse.persistence.jpa.modelgen.processor</artifactId>
            <version>2.7.12</version>
            <scope>provided</scope>
        </dependency>
```

Después, dentro de la carpeta Resources, he creado la carpeta de META-INF y el archivo de persistence.xml dentro de esta.

Configura la conexión de JPA con la base de datos de empleados, usando EclipseLink como proveedor, con la clase Empleado mapeada como entidad y nos genera automáticamente el esquema de la base de datos.

```http
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence           http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
    <persistence-unit name="jpaPU" transaction-type="RESOURCE_LOCAL">
        <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
        <class>org.example.logica.Empleado</class>


        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/empleados?serverTimezone=UTC"/>
            <property name="javax.persistence.jdbc.user" value="root"/>
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
            <property name="javax.persistence.schema-generation.database.action" value="create"/>
        </properties>
    </persistence-unit>
</persistence>
```

Y dentro de la carpeta de persistencia creo la carpeta de Exceptions que nos ayudará a manejar los errores de conexión con nuestra BBDD con el código facilitado por nuestro tutor

## Creación de clase

Las clases las creamos dentro de la carpeta de Logica para un mejor manejo

##Empleado

La clase Empleado es una entidad de JPA que representa una tabla en la base de datos con los campos: id, nombre, apellido, cargo, salario y fecha de inicio. El campo id es la clave primaria, autogenerada por la base de datos. La clase tiene constructores, getters, setters y un método toString() para mostrar la información de forma legible.




Importante irnos luego al archivo persistence.xml para añadir la clase.

```http
<class>org.example.logica.Empleado</class>
```


## Creación de Controlador Persistencia

Dentro de la carpeta de persistencia creamos la clase de controlador de persistencia. Esto nos ayuda a gestionar las operaciones CRUD de la entidad Empleado: crear, leer, actualizar y eliminar registros de la base de datos. También permite buscar empleados por su cargo. Usa la clase EmpleadoJpaController para interactuar con la base de datos y registra errores con Logger.

## Creación de Empleado JPA EmpleadoJpaController

La clase ControladorPersistencia permite gestionar los empleados en la base de datos con las operaciones CRUD (Crear, Leer, Actualizar y Eliminar). A diferencia del controlador de persistencia, radica en su propósito y nivel de abstracción y es la que se comunica directamente con la BBDD.



