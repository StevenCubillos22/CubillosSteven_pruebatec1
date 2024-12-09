package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorPersistencia {

    EmpleadoJpaController empleJPA = new EmpleadoJpaController();

    public void crearEmpleado(Empleado pers) {
        empleJPA.create(pers);
    }
    public void borrarEmpleado(Long id) {
        try {
            empleJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empleado traerEmpleado(Long id) {
        return empleJPA.findEmpleado(id);
    }

    public List<Empleado> traerEmpleados () {
        return empleJPA.findEmpleadoEntities();
    }

    public List<Empleado> traerEmpleadosPorCargo(String cargo) {
        List<Empleado> todos = empleJPA.findEmpleadoEntities();
        List<Empleado> encontrados = new ArrayList<>();

        for (Empleado emp : todos) {
            if (emp.getCargo().equalsIgnoreCase(cargo)) {
                encontrados.add(emp);
            }

        }
        return encontrados;
    }

    public void modificarEmpleado (Empleado pers) {

        try {
            empleJPA.edit(pers);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
