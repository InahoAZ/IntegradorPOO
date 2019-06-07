/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author miuz
 */
@Entity
@Table (name="especialidades")
public class Especialidad {
    @Id
    @GeneratedValue
    private int codEspecialidad;
    
    private String nombre;
    private String descripcion;
    
    @ManyToMany 
    private List<Medico> medicos = new ArrayList<>();

    public Especialidad() {
    }

    public Especialidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        
    }
    
    
    
}
