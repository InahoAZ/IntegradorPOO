/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author miuz
 */
@Entity
@Table (name="historias_clinicas")
public class HistoriaClinica {
    @Id
    @GeneratedValue
    private int codHistoria;

    public HistoriaClinica() {
    }
    private String descripcion;
    
    @OneToOne(mappedBy = "historiaC")
    private Paciente paciente;
}
