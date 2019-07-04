
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
    private Set<Especialidad> especialidades;
    
    @OneToMany (mappedBy = "medic")
    private List<Cita> citas;
    
   @OneToMany(mappedBy = "elmedico")
   private List<Registro> registros;
   
   private boolean activo;
    
    

    public Medico() {
        this.registros = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.especialidades = new HashSet();
    }

    public Medico(int tiempoTurno, int dni, String nombre, String apellido, int telefono) {
        super(dni, nombre, apellido, telefono);
        this.registros = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.especialidades = new HashSet();
        this.tiempoTurno = tiempoTurno;
    }

    public Medico(int dniInt) {
        super(dniInt);
        this.registros = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.especialidades = new HashSet();
        
    }
   public List  getCitasHoy(int dia){
    
        List lista = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita c1 = citas.get(i);
            if(c1.getFecha().getDate() == dia){
            
                lista.add(c1);
            
            }
        }
        
        return lista;
    
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

    public Set<Especialidad> getEspecialidades() {
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
        es.eliminarMedico(this);
        
    }
    
    
    @Override
    public String toString(){
    
        return  super.getDni() + "  " +super.getNombre() + "  " + super.getApellido() ;
        
    }

    public List getCitasLibres() {
        List lista = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita c1 = citas.get(i);
            if(c1.getElpaciente() == null){
            
                lista.add(c1);
            
            }
        }
        
        return lista;
    }

    public int getHoraMin(int dia) {
        
       
        List citashoy = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita c1 = citas.get(i);
            if(c1.getFecha().getDate() == dia){
                    citashoy.add(c1);
            }
            
            
        }
        Cita c1 = (Cita) citashoy.get(0);
        int min = c1.getHora();
        for (int i = 0; i < citashoy.size(); i++) {
             Cita caux = (Cita) citashoy.get(i);
            if(caux.getHora()<min){
                    
                    min = caux.getHora();
            
            }
        }
        return min;
    }

    public int getHoraMax(int dia) {
        
        List citashoy = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita c1 = citas.get(i);
            if(c1.getFecha().getDate() == dia){
                    citashoy.add(c1);
            }
            
            
        }
        Cita c1 = (Cita) citashoy.get(0);
        int max = c1.getHora();
        for (int i = 0; i < citashoy.size(); i++) {
             Cita caux = (Cita) citashoy.get(i);
            if(caux.getHora()>max){
                    
                    max = caux.getHora();
            
            }
        }
        return max;
        
        
    }

 
            
    
}
