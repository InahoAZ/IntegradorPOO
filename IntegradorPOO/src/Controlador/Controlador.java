
package Controlador;

import java.util.Date;
import java.util.List;
import modelo.Especialidad;
import modelo.HistoriaClinica;
import modelo.Medico;
import modelo.Paciente;


public class Controlador {
    Persistencia persistencia ;
    
    public Controlador(Persistencia p){
        this.persistencia = p ;
    }
    
public List listarMedicos(){
    return persistencia.buscarTodos(Medico.class);


}

    public void altaEspecialidad(String descrip, String nombre) 
        throws Exception{
        
        this.persistencia.iniciarTransaccion();
        
         try {
             Especialidad auxEspe = new Especialidad();
             auxEspe.setNombre(nombre);
             auxEspe.setDescripcion(descrip);
             this.persistencia.insertar(auxEspe);
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo cargar la especialidad");
        }
        
        
    }

    public List listarEspecialidades() {
        return persistencia.buscarTodos(Especialidad.class);
    }

    public void bajaCliente(int codEspecialidad) 
        throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            Especialidad auxEspe = this.persistencia.buscar(Especialidad.class, codEspecialidad);
            this.persistencia.eliminar(auxEspe);
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo borrar la especialidad");
        }
        
        
        
    }

    public void altaPaciente(String dni, String nombre, String apellido, String celular, Date nacimiento, String direccion)  throws Exception{
      
        this.persistencia.iniciarTransaccion();
        
         try {
             int num = Integer.parseInt(dni);
             int tel = Integer.parseInt(celular);
             Paciente auxPa = new Paciente(num);
             auxPa.setNombre(nombre);
             auxPa.setApellido(apellido);
             auxPa.setTelefono(tel);
             auxPa.setFnac(nacimiento);
             auxPa.setDireccion(direccion);
             
             //crear hc, con descripcion nulla
             HistoriaClinica auxHc = new HistoriaClinica();
             auxHc.setDescripcion("sin historia previa...");
             this.persistencia.insertar(auxHc);
             
             auxPa.setHistoriaC(auxHc);
            
             this.persistencia.insertar(auxPa);
             auxHc.setPaciente(auxPa);
             this.persistencia.modificar(auxHc);
             
             //modificar hc, agregando dni paciente
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo cargar el paciente");
        }
        
        
    }

    public List listarPacientes() {
        return persistencia.buscarTodos(Paciente.class);
    }

    public void modifPaciente(String dni, String nombre, String apellido, String celular, Date nacimiento, String direccion)  throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            
           int num = Integer.parseInt(dni);
             int tel = Integer.parseInt(celular);
             Paciente auxPa = new Paciente(num);
             auxPa.setNombre(nombre);
             auxPa.setApellido(apellido);
             auxPa.setTelefono(tel);
             auxPa.setFnac(nacimiento);
             auxPa.setDireccion(direccion);
             
            this.persistencia.modificar(auxPa);
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo modificar el paciente");
        }
        
        
        
    }
}
