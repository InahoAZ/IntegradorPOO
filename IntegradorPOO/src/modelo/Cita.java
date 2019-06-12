/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author miuz
 */
@Entity
@Table (name="citas")
public class Cita {
    @Id
    @GeneratedValue
    private int codCita;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    private boolean asistido;
    
    private Paciente elpaciente;
    
    @ManyToOne
    private Medico medic;
    
    
    
    public Cita() {
    }

    public Cita(Date fecha, boolean asistido, Paciente elpaciente, Medico medic) {
        this.fecha = new Date();
        this.asistido = asistido;
        this.elpaciente = elpaciente;
        this.medic = medic;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
