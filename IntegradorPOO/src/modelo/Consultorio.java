/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author miuz
 */
@Entity
@Table (name="consultorios")
public class Consultorio {    
    @Id
    private long cuit;

    public Consultorio() {
    }
    
    @OneToMany(mappedBy="consul")
    private List<Medico> medicos =new ArrayList<>();
    
    @OneToMany(mappedBy="consul")
    private List<Paciente> pacientes =new ArrayList<>();
    
    @OneToMany(mappedBy="consul")
    private List<Cita> citas  =new ArrayList<>();
    
    public Consultorio(long cuit) {
        this.cuit = cuit;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
    
    
    
    public void listarPacientes(){ //Devuelve una lista de todos los Pacientes 
        
    }
    
    public void listarMedicos(){ //Devuelve una lista de todos los Medicos
    
    }
    
    public void listarTurnos(Medico medico){ //Devuelve una lista de todos los Turnos 
        
    }    
        
    public void obtenerRecordatorios(){
        
    }
    
    public void cargarFormPaciente(){
    
    }
    
    public void crearCita(Paciente paciente, Medico medico){
                
    }
    
    public void modificarCita(){
        
    }
    
    public void verificarCitaDisponible(){
        
    }
    
    
}
