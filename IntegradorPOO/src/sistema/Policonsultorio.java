package sistema;

import Vista.VentanaPrincipal;
import controlador.Controlador;
import controlador.Persistencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Policonsultorio {

    public static void main(String[] args) {
        // TODO code application logic here
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PoliconsultorioPU");
    
        Persistencia p = new Persistencia(emf);
        Controlador c1 = new Controlador(p);
        
        VentanaPrincipal p1 = new VentanaPrincipal(c1);
        
        
        
    }
    
}
