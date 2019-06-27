
package modelo;

import java.util.ArrayList;
import java.util.List;
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
    private List<Medico> medicos;

    public Especialidad() {
        this.medicos = new ArrayList<>();
        this.estadoActivo = true;
    }

    public Especialidad(String nombre, String descripcion) {
        this.medicos = new ArrayList<>();
        this.nombre = nombre;
        this.descripcion = descripcion;
         this.estadoActivo = true;
        
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

    
    
    
    @Override
    public String toString(){

        return this.codEspecialidad + " " + this.estadoActivo + "  "+nombre;
    }
    
    
    
}
