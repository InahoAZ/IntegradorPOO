
package Controlador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import modelo.Cita;
import modelo.Especialidad;
import modelo.Paciente;

public class Persistencia {
        private final EntityManager em;

    public Persistencia(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public void iniciarTransaccion() {
        em.getTransaction().begin();
    }

    public void confirmarTransaccion() {
        em.getTransaction().commit();
    }

    public void descartarTransaccion() {
        em.getTransaction().rollback();
    }

    public void insertar(Object o) {
        this.em.persist(o);
    }

    public void modificar(Object o) {
        this.em.merge(o);
    }

    public void eliminar(Object o) {
        this.em.remove(o);
    }

    public void refrescar(Object o) {
        this.em.refresh(o);
    }

    public <T extends Object> T buscar(Class<T> clase, Object id) {
        return (T) this.em.find(clase, id);
    }

    public <T extends Object> List<T> buscarTodos(Class<T> clase) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        Root<T> inicio = consulta.from(clase);
        return em.createQuery(consulta).getResultList();
    }

    public <T extends Object, P extends Object> List<T> buscarTodosOrdenadosPor(Class<T> clase, SingularAttribute<T, P> orden) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        Root<T> inicio = consulta.from(clase);
        consulta.orderBy(cb.asc(inicio.get(orden)));
        return em.createQuery(consulta).getResultList();
    }
    
    
    public <T extends Object> List<T> buscarTodosCitas(int dia,int dni) {
      
         List<Object[]> gList = new ArrayList<>();
         Query query = em.createNativeQuery("select codcita, asistido, estado, fecha, hora, medic_dni, elpaciente_dni from citas where medic_dni =  "+dni+ "  and extract(day from fecha) =  "+dia);
         gList.addAll(query.getResultList());
         return (List<T>) gList;        
        
    }

   public int obtenerHoraVieja(int i,int dni,int dia) {
        
        int fecha;
        Query query;
        if(i==1){
        //obtiene fecha mas peque
                   query = em.createNativeQuery("select min(hora) from citas where medic_dni = "+dni+" and extract(day from fecha) =  "+dia);
        }else{
         //obtiene fecha mas grandotota
                   query = em.createNativeQuery("select max(hora) from citas where medic_dni = "+dni+" and extract(day from fecha) =  "+dia);
        }
        
        fecha = Integer.parseInt(query.getResultList().get(0).toString());
         
        return fecha;
        
    }
   
   public <T extends Object> List<T> listarMedicosActivos() {
      
         List<Object[]> gList = new ArrayList<>();
         Query query = em.createNativeQuery("select m.dni, p.apellido, p.nombre from personas p, medico m where m.activo = true AND p.dni = m.dni");
         gList.addAll(query.getResultList());
         return (List<T>) gList;        
        
    }

    public <T extends Object> List<T> buscarCitas(Paciente auxP) {
        
         List<Object[]> gList = new ArrayList<>();
         Query query = em.createNativeQuery("select codcita, asistido, estado, fecha, hora, medic_dni, elpaciente_dni from citas where elPaciente_dni =  "+auxP.getDni());
         gList.addAll(query.getResultList());
         return (List<T>) gList;
        
    }

    

}
