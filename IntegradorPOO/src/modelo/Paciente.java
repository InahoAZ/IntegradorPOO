/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author miuz
 */
@Entity
@Table (name="pacientes")
class Paciente{

    @Id
    private int dni;
    private String nombre;
    private String apellido;
    private int telefono;
    
    private String direccion;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fnac;   
    
    private HistoriaClinica historiaC;
    
    @ManyToOne
    private Consultorio consul;

    public Paciente() {
    }
    
    
    
    public void obtenerHC(Paciente paciente){ //Devuelve el Historial Clinico de un Paciente       
           
    }
}
