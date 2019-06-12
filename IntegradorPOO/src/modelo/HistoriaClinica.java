/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author miuz
 */
@Entity
@Table (name="historias_clinicas")
public class HistoriaClinica {
    //Atributos
    @Id
    @GeneratedValue
    private int codHistoria;

    
    private String descripcion;
    
    private Paciente paciente;
    
    @OneToMany(mappedBy = "hc")
    private ArrayList<Registro> registro = new ArrayList<>();
    
    
    //Constructores
    public HistoriaClinica() {
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
        return registro;
    }

    
    public void nuevoRegistro(String diagnostico){        
        Registro reg = new Registro(diagnostico);        
        registro.add(reg);
    
    }
    
    
    
    
}
