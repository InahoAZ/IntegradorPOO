
package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name="especialidades")
public class Especialidad {
    @Id
    @SequenceGenerator(name="sec_codEspecialidad", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sec_codEspecialidad")
    private int codEspecialidad;
    
    private String nombre;
    private String descripcion;
    
    private boolean estadoActivo;
    
    @ManyToMany 
    private Set<Medico> medicos;

    public Especialidad() {
        this.medicos = new HashSet();
        this.estadoActivo = true;
    }

    public Especialidad(String nombre, String descripcion) {
        this.medicos = new HashSet();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadoActivo = true;
        
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodEspecialidad() {
        return codEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void agregarMedico(Medico m){
        this.medicos.add(m);
    }
    
    public void eliminarMedico(Medico m){
        this.medicos.remove(m);                
    }

    
    
    
    @Override
    public String toString(){

        return this.codEspecialidad + " " + this.estadoActivo + "  "+nombre;
    }
    
    
    
}
