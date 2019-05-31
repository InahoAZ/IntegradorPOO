/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue("persona")
public class Paciente extends Persona{

  
    
    private String direccion;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fnac;   
    
    private HistoriaClinica historiaC;
    
    
    private Cita miCita;
    
   

    public Paciente() {
    }
    
    
    
    public void obtenerHC(Paciente paciente){ //Devuelve el Historial Clinico de un Paciente       
           
    }
}
