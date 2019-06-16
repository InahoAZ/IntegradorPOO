
package Controlador;

import java.util.List;
import modelo.Especialidad;
import modelo.Medico;


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
}
