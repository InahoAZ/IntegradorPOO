
package modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table (name="citas")
public class Cita {
    @Id
    @SequenceGenerator(name="sec_codCitas", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sec_codCitas")
    private int codCita;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    private boolean asistido;
    
    @OneToOne
    private Paciente elpaciente;
    
    @ManyToOne
    private Medico medic;
    
    private int hora;
    
    private boolean estado;
    
    private boolean recordado;
    
    
    
    public Cita() {
        this.asistido = false;
        this.recordado = false;
        this.estado = true;
    }

    public boolean isRecordado() {
        return recordado;
    }

    public void setRecordado(boolean recordado) {
        this.recordado = recordado;
    }

    public Cita(Date fecha,  Paciente elpaciente, Medico medic) {
        this.fecha = new Date();
        this.asistido = false;
        this.elpaciente = elpaciente;
        this.estado = true;
        this.medic = medic;
        this.recordado = false;
    }

    public boolean isEstado() {
        return estado;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCodCita() {
        return codCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean isAsistido() {
        return asistido;
    }

    public Paciente getElpaciente() {
        return elpaciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Medico getMedic() {
        return medic;
    }

    public void setAsistido(boolean asistido) {
        this.asistido = asistido;
    }

    public void setElpaciente(Paciente elpaciente) {
        this.elpaciente = elpaciente;
    }

    public void setMedic(Medico medic) {
        this.medic = medic;
    }

    @Override
    public String toString() {
        
        return  codCita + "   " + pasarToDay(fecha.getDay()) + "  "+fecha.getDate() +"  "+hora+ "  " + asistido +"   "+ estado+"    " + medic.getDni() + "  " + elpaciente;
    }
    
    
    
    private String pasarToDay(int dia){
    
    
                 switch(dia){
                     
                     case 0: return "Dom";
                     case 1: return "Lun";
                     case 2: return "Mar";
                     case 3: return "Mie";
                     case 4: return "Jue";
                     case 5: return "Vie";
                     case 6: return "Sab";
                 
                 
                     default: return "not";
                 }
    
    
    }
    
    
    
    
    
    
    
    
    
    
}
