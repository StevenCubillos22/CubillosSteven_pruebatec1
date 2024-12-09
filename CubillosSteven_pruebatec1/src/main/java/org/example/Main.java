package org.example;
import java.util.ArrayList;
import java.util.List;
import org.example.logica.Empleado;
import org.example.persistencia.ControladorPersistencia;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //invocamos la controladora de la persistencia
        ControladorPersistencia controlPersis = new ControladorPersistencia();

        //VARIABLES
        //booleano que nos servira para estar dentro del CRUD
        boolean salir = false;

        //Variables dentro del switch que nos servirá coger las variables e introducirla en los empleado
        int seleccion = 0;
        long idEmpl;
        String nombreSwitch = "";
        String apellidoSwitch = "";
        String cargoSwitch = "";
        double salarioSwitch = 0;


        while (!salir) {
            System.out.println("\n===== Menú CRUD Empleados de la empresa Your Task =====");

            System.out.println("\n1. Crear nuevo empleado");
            System.out.println("\n2. Mostrar lista de empleados");
            System.out.println("\n3. Actualizar detalles de un empleado");
            System.out.println("\n4. Eliminar un empleado");
            System.out.println("\n5. Buscar empleados por cargo");
            System.out.println("\n6. Salir");
            seleccion = sc.nextInt();
            sc.nextLine();

            switch (seleccion) {
                    case 1:  // TERMINAR los validadores
                        System.out.println("Ingrese el nombre del nuevo empleado");
                        System.out.println("   ");
                        nombreSwitch = sc.nextLine();
                        while (nombreSwitch.trim().isEmpty()) {
                            System.out.print("El campo no puede estar vacío. Ingrese el nombre nuevamente: ");
                            nombreSwitch = sc.nextLine();
                        }

                        System.out.println("Ingrese el apellido del nuevo empleado");
                        apellidoSwitch = sc.nextLine();
                        while (apellidoSwitch.trim().isEmpty()) {
                            System.out.print("El campo no puede estar vacío. Ingrese el apellido nuevamente: ");
                            apellidoSwitch = sc.nextLine();
                        }

                        System.out.println("Ingrese el cargo de la nueva empleado");
                        cargoSwitch = sc.nextLine();
                        while (cargoSwitch.trim().isEmpty()) {
                            System.out.print("El campo no puede estar vacío. Ingrese el cargo nuevamente: ");
                            cargoSwitch = sc.nextLine();
                        }

                        System.out.println("Ingrese el salario del nuevo empleado");
                        salarioSwitch = sc.nextDouble();


                        Empleado emp = new Empleado(nombreSwitch, apellidoSwitch, cargoSwitch, salarioSwitch, new Date());
                        controlPersis.crearEmpleado(emp);
                        System.out.println("Se está creando el nuevo empleado");
                        System.out.println(emp);
                        break;

                    case 2:
                        List<Empleado> emps = new ArrayList<>();
                        emps = controlPersis.traerEmpleados();
                        if (emps.isEmpty()) {
                            System.out.println("No hay empleados en la BBDD");
                        } else {
                            for (Empleado e : emps) {
                                System.out.println(e);
                                System.out.println("    -      -    ");
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Ingrese el id del empleado para editarlo");
                        idEmpl = sc.nextLong();
                        sc.nextLine();
                        Empleado empEditar = controlPersis.traerEmpleado(idEmpl);


                        if (empEditar == null) {
                            System.out.println("El ID no se corresponde a un empleado");
                        }

                        int opcion;
                        System.out.println("Di que atributo quieres editar");
                        System.out.println("1. Nombre");
                        System.out.println("2. Apellido");
                        System.out.println("3. Cargo");
                        System.out.println("4. Salario");
                        System.out.println("5. Todos los atributos");

                        opcion = sc.nextInt();
                        sc.nextLine();
                        switch (opcion) {
                            case 1:
                                System.out.println("Ingrese el nombre del empleado");
                                nombreSwitch = sc.nextLine();
                                empEditar.setNombre(nombreSwitch);
                                break;

                            case 2:
                                System.out.println("Ingrese el apellido del empleado");
                                apellidoSwitch = sc.nextLine();
                                empEditar.setApellido(apellidoSwitch);
                                break;

                            case 3:
                                System.out.println("Ingrese el cargo del empleado para editarlo");
                                cargoSwitch = sc.nextLine();
                                empEditar.setCargo(cargoSwitch);
                                break;

                            case 4:
                                System.out.println("Ingrese el salario del empleado para editarlo");
                                salarioSwitch = sc.nextDouble();
                                empEditar.setSalario(salarioSwitch);
                                break;

                            case 5:
                                System.out.println("Ingrese el nombre del empleado para editarlo");
                                nombreSwitch = sc.nextLine();
                                empEditar.setNombre(nombreSwitch);

                                System.out.println("Ingrese los apellidos del empleado para editarlo");
                                apellidoSwitch = sc.nextLine();
                                empEditar.setApellido(apellidoSwitch);

                                System.out.println("Ingrese el cargo del empleado para editarlo");
                                cargoSwitch = sc.nextLine();
                                empEditar.setCargo(cargoSwitch);

                                System.out.println("Ingrese el salario del empleado para editarlo");
                                salarioSwitch = sc.nextDouble();
                                empEditar.setSalario(salarioSwitch);

                                System.out.println("Todo se actualizó correctamente");
                                break;

                            default:
                                System.out.println("La opcion no es valida");
                            break;
                        }
                        controlPersis.modificarEmpleado(empEditar);
                        break;

                    case 4:
                        System.out.println("Ingrese el id del empleado para poder eliminarlo correctamente");
                        idEmpl = Long.parseLong(sc.nextLine());
                        sc.nextLine();
                        Empleado empEliminar = controlPersis.traerEmpleado(idEmpl);

                        if(empEliminar == null) {
                            System.out.println("El empleado no se ha encontrado para eliminar");
                            break;
                        }

                        controlPersis.borrarEmpleado(idEmpl);
                        System.out.println("Se ha eliminado el empleado correctamente");
                        break;

                    case 5:
                        System.out.println("Ingrese el cargo para mostrar los empleados que le corresponda");
                        cargoSwitch = sc.nextLine();

                        List<Empleado> empsCargo = controlPersis.traerEmpleadosPorCargo(cargoSwitch);
                        for (Empleado e : empsCargo) {
                            System.out.println(" ");
                            System.out.println(e);
                            System.out.println("    -      -    ");
                        }
                        break;

                    case 6:
                        salir = true;
                        System.out.println("La aplicación se ha cerrado sin problemas");
                        break;
            }

        }

    }
}