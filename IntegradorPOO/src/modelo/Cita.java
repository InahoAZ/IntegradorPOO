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

    public Cita(int codCita, Date fecha, boolean asistido) {
        this.codCita = codCita;
        this.fecha = fecha;
        this.asistido = asistido;
    }
    
    
    
    
}
