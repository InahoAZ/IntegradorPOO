
package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@DiscriminatorValue("medico")
public class Medico extends Persona{    
    @SequenceGenerator(name="sec_matricula", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sec_matricula") 
    private long matricula;
    private int tiempoTurno;
    private static int horasLaboral; // Parametro de las horas trabajadas para calcular citas disponibles    
    
    @ManyToMany (mappedBy = "medicos")    
    private List<Especialidad> especialidades;
    
    @OneToMany (mappedBy = "medic")
    private List<Cita> citas;
    
   @OneToMany(mappedBy = "elmedico")
   private List<Registro> registros;
   
   private boolean activo;
    
    

    public Medico() {
        this.registros = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.especialidades = new ArrayList<>();
    }

    public Medico(long matricula, int tiempoTurno, int dni, String nombre, String apellido, int telefono) {
        super(dni, nombre, apellido, telefono);
        this.registros = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.especialidades = new ArrayList<>();
        this.matricula = matricula;
        this.tiempoTurno = tiempoTurno;
    }

    public Medico(int dniInt) {
        super(dniInt);
        this.registros = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.especialidades = new ArrayList<>();
        
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
    
    public void agregarEspecialidad(Especialidad es){
        System.out.println(es.getDescripcion());
        this.especialidades.add(es);
        es.agregarMedico(this);
        System.out.println("Se agrego");
        
    }
    
    public void eliminarEspecialidad(Especialidad es){
        this.especialidades.remove(es);
        
    }
    
    
    @Override
    public String toString(){
    
        return  super.getDni() + "  " +super.getNombre() + "  " + super.getApellido() ;
        
    }

 
            
    
}
