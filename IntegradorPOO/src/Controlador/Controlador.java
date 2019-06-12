
package controlador;

import java.util.List;
import modelo.Medico;


public class Controlador {
    Persistencia persistencia ;
    
    public Controlador(Persistencia p){
        this.persistencia = p ;
    }
    
public List listar(){
    return    persistencia.buscarTodos(Medico.class);


}
}