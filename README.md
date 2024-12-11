


![portadapt1](https://github.com/user-attachments/assets/51d65af9-8de9-4732-b1f9-8b72c26142ac)




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

Para asegurar la conexion con la BBDD, primero que todo, vamos a nuestro archivo pom.xml para agregar la dependencia de MySQL dentro la la misma de dependencias y luego agregar el archivo jar


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

Y ya para añadir el archivo jar, he accedido a la opción de module settings para añadirlo.

![Captura de pantalla 2024-12-10 195140](https://github.com/user-attachments/assets/25d7f90b-e402-4178-bc23-d42403b0fa97)


![Captura de pantalla 2024-12-10 195118](https://github.com/user-attachments/assets/b9f16575-e4a6-41c6-9def-3df0dfeee21c)




Después, dentro de la carpeta Resources, he creado la carpeta de META-INF y el archivo de persistence.xml dentro de esta.

![image](https://github.com/user-attachments/assets/11d5a4dd-d052-4f50-ad59-83bd2e84a74d)


En el archivo, configuramos la conexión de JPA con la base de datos de empleados, usando EclipseLink como proveedor, con la clase Empleado mapeada como entidad y nos genera automáticamente el esquema de la base de datos.

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

![Captura de pantalla 2024-12-10 200343](https://github.com/user-attachments/assets/adf295e8-78f7-49b6-8d3d-fed67ef21fb3)

## Creación de clase

Las clases las creamos dentro de la carpeta de Logica para un mejor manejo

## Empleado

La clase Empleado es una entidad de JPA que representa una tabla en la base de datos con los campos: id, nombre, apellido, cargo, salario y fecha de inicio. El campo id es la clave primaria, autogenerada por la base de datos. La clase tiene constructores, getters, setters y un método toString() para mostrar la información de forma legible.


![Captura de pantalla 2024-12-10 200944](https://github.com/user-attachments/assets/bf5cc6a7-7931-40fd-b23e-77c3ee897939)

Importante irnos luego al archivo persistence.xml para añadir la clase.

```http
<class>org.example.logica.Empleado</class>
```


## Creación de Controlador Persistencia

![image](https://github.com/user-attachments/assets/90212f25-f806-4f10-ab4d-115a5379cca9)

Dentro de la carpeta de persistencia creamos la clase de controlador de persistencia. Esto nos ayuda a gestionar las operaciones CRUD de la entidad Empleado: crear, leer, actualizar y eliminar registros de la base de datos. También permite buscar empleados por su cargo. Usa la clase EmpleadoJpaController para interactuar con la base de datos y registra errores con Logger.



## Creación de Empleado JPA EmpleadoJpaController

![image](https://github.com/user-attachments/assets/3bea2afc-ef08-4adc-908f-fd13153f6168)


La clase ControladorPersistencia permite gestionar los empleados en la base de datos con las operaciones CRUD (Crear, Leer, Actualizar y Eliminar). A diferencia del controlador de persistencia, radica en su propósito y nivel de abstracción y es la que se comunica directamente con la BBDD.


## Main

Ahora vamos con el archivo Main que es el punto de entrada de la aplicación y permite al usuario interactuar con un CRUD de empleados a través de un menú de opciones. Este menú se repite dentro de un bucle while hasta que el usuario seleccione la opción de salir. Cada opción está gestionada con un switch-case, donde se llaman a las funciones necesarias para crear, leer, actualizar y eliminar empleados.

![image](https://github.com/user-attachments/assets/f73ee2f2-e9e6-4019-9a57-58909901f094)



| **Case**   | **Acción**            | **Descripción**                                                                                       | **Funciones Usadas**                                                 |
|------------|----------------------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| **1**      | **Crear Empleado**    | Solicita los datos del empleado (nombre, apellido, cargo y salario), crea un objeto `Empleado` y lo guarda en la base de datos. | `controlPersis.crearEmpleado(emp)`                                  |
| **2**      | **Mostrar Empleados** | Obtiene la lista de todos los empleados de la base de datos y los muestra en la consola.                | `controlPersis.traerEmpleados()`                                    |
| **3**      | **Editar Empleado**   | Permite modificar los atributos de un empleado por su ID. Se puede editar nombre, apellido, cargo, salario o todos a la vez. | `controlPersis.traerEmpleado(idEmpl)`, `controlPersis.modificarEmpleado(empEditar)` |
| **4**      | **Eliminar Empleado** | Solicita el ID de un empleado y lo elimina de la base de datos. Verifica si existe antes de eliminar.   | `controlPersis.traerEmpleado(idEmpl)`, `controlPersis.borrarEmpleado(idEmpl)`      |
| **5**      | **Buscar por Cargo**  | Solicita un cargo e imprime la lista de empleados que tienen ese cargo en la base de datos.            | `controlPersis.traerEmpleadosPorCargo(cargoSwitch)`                 |
| **6**      | **Salir**             | Finaliza el programa y muestra un mensaje de cierre.                                                   | No usa funciones de persistencia                                    |


## Organización del proyecto

Y ya para terminar quiero mostrar como quedó la organización del proyecto en como he estructurado los archivos y carpetas:

![Captura de pantalla 2024-12-10 204640](https://github.com/user-attachments/assets/361085d1-3075-4d9f-bf09-9fbba4827792)
