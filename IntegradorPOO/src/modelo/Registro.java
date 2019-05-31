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
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Arnold Zarske
 */
@Entity
@Table (name = "registros")
public class Registro {
        
        @Id
        @GeneratedValue
        private int cod;
        @Temporal(javax.persistence.TemporalType.DATE)
        private Date fecha;
        private String disgnostico;
        
        //@ arreglar primero en medico
        //private Medico elmedico;

    public Registro() {
    }

    public Registro(String disgnostico) {
        this.fecha = new Date();
        this.disgnostico = disgnostico;
    }

    public int getCod() {
        return cod;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDisgnostico() {
        return disgnostico;
    }
        
        
    
    
    
    
}
