
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name="historias_clinicas")
public class HistoriaClinica {
    //Atributos
    @Id
    @SequenceGenerator(name="sec_codHC", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sec_codHC")
    private int codHistoria;

    
    private String descripcion;
    
    @OneToOne
    private Paciente paciente;
    
    @OneToMany(mappedBy = "hc")
    private List<Registro> registros;  //problema
    
    
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

    public int getCodHistoria() {
        return codHistoria;
    }


    public List<Registro> getRegistros() {
        return registros;
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

    
    public List<Registro> getRegistro() {
        return registros;
    }

    
    public void nuevoRegistro(String diagnostico){        
        Registro reg = new Registro(diagnostico);        
        registros.add(reg);
    
    }
    
    
    
    
}
