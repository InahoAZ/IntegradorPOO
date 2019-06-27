
package modelo;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


@Entity
@DiscriminatorValue("persona")
public class Paciente extends Persona{ 
    
    private String direccion;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fnac;    
    
    @OneToOne
    private HistoriaClinica historiaC;
    
    @OneToOne
    private Cita miCita;   
    
    private boolean estado; 

    public Paciente() {
        estado = true;
    }

    public Paciente(int dni) {
        super(dni);
        estado = true;
    }
    
    

    public Paciente(String direccion, Date fnac, HistoriaClinica historiaC, Cita miCita, int dni, String nombre, String apellido, int telefono) {
        super(dni, nombre, apellido, telefono);
        this.direccion = direccion;
        this.fnac = fnac;
        this.historiaC = historiaC;
        this.miCita = miCita;
        estado = true;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFnac() {
        return fnac;
    }

    public void setFnac(Date fnac) {
        this.fnac = fnac;
    }

    public HistoriaClinica getHistoriaC() {
        return historiaC;
    }

    public void setHistoriaC(HistoriaClinica historiaC) {
        this.historiaC = historiaC;
    }

    public Cita getMiCita() {
        return miCita;
    }

    public void setMiCita(Cita miCita) {
        this.miCita = miCita;
    }
    
    
    
    
    
    public void obtenerHC(Paciente paciente){ //Devuelve el Historial Clinico de un Paciente       
           
    }
    
    
    
    @Override
    public String toString(){
    
                return this.getDni()+"      "+this.getApellido()+"      "+this.getNombre()+"   "+this.estado;
    
    }
}
