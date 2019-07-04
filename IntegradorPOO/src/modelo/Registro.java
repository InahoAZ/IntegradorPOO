
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name = "registros")
public class Registro {
    //Atributos
    
    @Id
    @GeneratedValue
    private int cod;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private String disgnostico;

     @ManyToOne
    private Medico elmedico;
    
    @ManyToOne
    private HistoriaClinica hc;
    
    
    //Constructores
    
    public Registro() {
    }

    public Registro(String disgnostico, Medico medico, HistoriaClinica hc) {
        this.fecha = new Date();
        this.disgnostico = disgnostico;
        this.elmedico = medico;
        this.hc = hc;
    }
    
    //Metodos
    
    public int getCod() {
        return cod;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public String getFechaLinda(){
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        return dt1.format(this.fecha);
    }

    public String getDisgnostico() {
        return disgnostico;
    }

    @Override
    public String toString() {
        
        
        return "" + "" + cod + "  " + getFechaLinda() + "  " + elmedico + "";
    }
    
    
        
        
    
    
    
    
}
