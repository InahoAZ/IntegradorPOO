/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author miuz
 */
@Entity
@DiscriminatorValue("medico")
public class Medico extends Persona{
    
    
    private long matricula;
    
    private int tiempoTurno;
    private static int horasLaboral;    
    
    @ManyToMany (mappedBy = "medicos")    
    private List<Especialidad> especialidades= new ArrayList<>();
    
    @OneToMany (mappedBy = "medic")
    private List<Cita> citas = new ArrayList<>();
    
    

    public Medico() {
    }
    
    
    
            
    
}
