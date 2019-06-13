
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="especialidades")
public class Especialidad {
    @Id
    @GeneratedValue
    private int codEspecialidad;
    
    private String nombre;
    private String descripcion;
    
    @ManyToMany 
    private List<Medico> medicos;

    public Especialidad() {
        this.medicos = new ArrayList<>();
    }

    public Especialidad(String nombre, String descripcion) {
        this.medicos = new ArrayList<>();
        this.nombre = nombre;
        this.descripcion = descripcion;
        
    }

    @Override
    public String toString(){

        return this.codEspecialidad + " " + this.nombre + " " + this.descripcion;
    }
    
    
    
}
