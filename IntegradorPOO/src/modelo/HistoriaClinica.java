
package modelo;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name="historias_clinicas")
public class HistoriaClinica {
    //Atributos
    @Id
    @GeneratedValue
    private int codHistoria;

    
    private String descripcion;
    
    @OneToOne
    private Paciente paciente;
    
    @OneToMany(mappedBy = "hc")
    private ArrayList<Registro> registros;
    
    
    //Constructores
    public HistoriaClinica() {
        
    }

    public HistoriaClinica(Registro reg) {
        registros = new ArrayList<>();
        this.registros.add(reg);
    }
   
    
    
    
    //Metodos    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    public ArrayList<Registro> getRegistro() {
        return registros;
    }

    
    public void nuevoRegistro(String diagnostico){        
        Registro reg = new Registro(diagnostico);        
        registros.add(reg);
    
    }
    
    
    
    
}
