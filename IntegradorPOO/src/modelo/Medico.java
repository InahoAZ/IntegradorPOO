/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
    
   @OneToMany(mappedBy = "elmedico")
   private List<Registro> registros = new ArrayList<>();
    
    

    public Medico() {
    }

    public Medico(long matricula, int tiempoTurno, int dni, String nombre, String apellido, int telefono) {
        super(dni, nombre, apellido, telefono);
        this.matricula = matricula;
        this.tiempoTurno = tiempoTurno;
    }

    public int getTiempoTurno() {
        return tiempoTurno;
    }

    public void setTiempoTurno(int tiempoTurno) {
        this.tiempoTurno = tiempoTurno;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public long getMatricula() {
        return matricula;
    }

    public static int getHorasLaboral() {
        return horasLaboral;
    }

    public static void setHorasLaboral(int horasLaboral) {
        Medico.horasLaboral = horasLaboral;
    }

    public void setEspecialidades(Especialidad especialidad) {
        this.especialidades.add(especialidad);
    }

    public void setCitas(Cita cita) {
        this.citas.add(cita);
    }
    
    
    //aqui el metodo busca las citas que pertenesca a "dni" y sean de un determinado "fecha"
    /*public Cita buscarCita(int dni, Date fecha){
            Cita c;
            
           return c;
        
    }*/
    
    //ERROR -- agregar una que por paciente nomas devuelva la Hc.
    //devuelve la historia clinica de un paciente obtenido por la cita
    /*public HistoriaClinica obtenerHistClinica(Cita c){
            HistoriaClinica h;
            
           return h;
    
    }*/
            
    
}
