
package Controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ListModel;
import modelo.Cita;
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
    
    public void altaMedico(String dni, String apellido, String nombre, String telefono, int tiempoTurno, ListModel esp )
        throws Exception{
        this.persistencia.iniciarTransaccion();

        try{

            int dniInt = Integer.parseInt(dni);
            int telfInt = Integer.parseInt(telefono);
            
            Medico auxMed = new Medico(dniInt);        
            auxMed.setApellido(apellido);
            auxMed.setNombre(nombre);
            auxMed.setTelefono(telfInt);
            auxMed.setTiempoTurno(tiempoTurno);
            auxMed.setEspecialidades((Especialidad)esp);
            //Especialidad e1 = new Especialidad("Pediatra", "Descripcion");
            //auxMed.setEspecialidades(e1);
            
            this.persistencia.insertar(auxMed);
            //uxMed.setEspecialidades((Especialidad) esp.getElementAt(0));
            this.persistencia.confirmarTransaccion();

        } catch (Exception e){
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo cargar el Medico");

}
        }
    public void modificarMedico(int dni, String apellido, String nombre, String telefono, int tTurno)
    throws Exception{
        this.persistencia.iniciarTransaccion();

        try{
            Medico m = new Medico(dni);            
            m.setNombre(nombre);
            m.setApellido(apellido);
            m.setTelefono(Integer.parseInt(telefono));
            m.setTiempoTurno(tTurno);            
            this.persistencia.modificar(m);
            this.persistencia.confirmarTransaccion();
        }catch(Exception e){        
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo cargar el Medico");
        }
        
    
    
    
    
    }
    
    public void addEspecialidadMedico(int dni, Especialidad esp)
        throws Exception {
        this.persistencia.iniciarTransaccion();
        try {
            Medico auxMedico = this.persistencia.buscar(Medico.class, dni);
            auxMedico.setEspecialidades(esp);            
            this.persistencia.modificar(auxMedico);
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo añadir la Especialidad");
        }
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

    public void bajaEspecialidad(int codEspecialidad) 
        throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            Especialidad auxEspe = this.persistencia.buscar(Especialidad.class, codEspecialidad);
            auxEspe.setEstadoActivo(false);
            this.persistencia.modificar(auxEspe); // merger con cambio en boorado
            
            
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

    public void eliminarMedico(Medico m)
    throws Exception{
        this.persistencia.iniciarTransaccion();
        try{
            this.persistencia.eliminar(m);
        
        }catch(Exception e){
            
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
    
    public void generarCitas(Date inicio, Date fin) {
    
            int fechaInicioDia =  (inicio.getHours() * 100 )+(inicio.getMinutes());
            int fechaFinalDia =  (fin.getHours() * 100 )+(fin.getMinutes());
            int tomorrow = inicio.getDate() + 1; // hoy en ej:23  + 1 = 24(tomorrow)
            int finalDayMes = obtenerCantDias(inicio.getMonth()+1); //entre 0 a 31
             int auxHoraDiaInicio;
             boolean hoyTieneCitas;
            Date algebraHora = new Date();
            Date diaHoy = new Date();
            //obtener medicos
             List <Medico> misMedicos = new ArrayList<>(listarMedicos());
             int horaInicioViejo=0;
             int horaFinalViejo=0;
             
             if(misMedicos.size()>0){
                 
                 for (int i = tomorrow; i <= finalDayMes; i++) {
                     
                            System.out.println("dia actual: " + i );
                            
                            for (int m = 0; m < misMedicos.size(); m++) {
                                    Medico m1 = misMedicos.get(m);
                                    System.out.println("medico n: "+m);
                                    System.out.println(m1);
                                    
                                    auxHoraDiaInicio= fechaInicioDia;
                                    algebraHora.setHours(inicio.getHours());
                                    algebraHora.setMinutes(inicio.getMinutes());
                                    
                                    diaHoy.setDate(i);
                                    
                                    
                                      //obtener cantidad de citas de m1 para el dia de hoy... si tiene minimo una cita para hoy hoyTieneCitas = true;
                                      
                                     hoyTieneCitas = isHoyCita(diaHoy.getDate(),m1);
                                      
                                      if(hoyTieneCitas ){
                                                 //obtener hora vieja (inicio y final)
                                                 horaInicioViejo =  horaMedicVieja(1,m1.getDni(),diaHoy.getDate());//pasar el dia de hoy
                                                 horaFinalViejo =  horaMedicVieja(2,m1.getDni(),diaHoy.getDate());//pasar el dia de hoy
                                      }
                                      
                                     while(auxHoraDiaInicio<fechaFinalDia ){
                                     
                                         
                                           
                                            
                                            if(! hoyTieneCitas){ //si el medico es nuevo ( no tiene citas)
                                            
                                                    System.out.println("cita creada");
                                                    altaCita(diaHoy,auxHoraDiaInicio,m1);

                                                    algebraHora.setMinutes(algebraHora.getMinutes()+m1.getTiempoTurno());
                                                    auxHoraDiaInicio = (algebraHora.getHours() * 100 )+(algebraHora.getMinutes());
                                                
                                            }else{ //si el medico ya tenia citas, se le agrega las extra (si el horario fue ampliado)
                                                
                                               //calcular si puede crear la cita
                                               //sino simplemente ir al final de hora vieja
                                       
                                               if(  ( (auxHoraDiaInicio + m1.getTiempoTurno() ) < horaInicioViejo ) ||   (horaFinalViejo<auxHoraDiaInicio) ){//si puedo crear
                                                    
                                                        
                                                      System.out.println("cita creada");
                                                      altaCita(diaHoy,auxHoraDiaInicio,m1);
                                                      algebraHora.setMinutes(algebraHora.getMinutes()+m1.getTiempoTurno());
                                                      auxHoraDiaInicio = (algebraHora.getHours() * 100 )+(algebraHora.getMinutes());
                                                
                                                }else{
                                                    //se podria modificar las 2 lineas siguientes por 
                                                    //tomar simplemente el valor de fin viejo
                                                    algebraHora.setMinutes(algebraHora.getMinutes()+m1.getTiempoTurno());
                                                    auxHoraDiaInicio = (algebraHora.getHours() * 100 )+(algebraHora.getMinutes());
                                                        
                                                
                                                }
                                                      
                                                
                                            
                                            }
                                            
                                            
                                            
                                            
                                     
                                     }
                                    
                            
                            } 
                 
                 }
             
             
             
             }
             
             
            
            
    }

    
    

    private int obtenerCantDias(int mes) {
        
        
        switch(mes){
        
            case 1: return 31;
            case 2: return 28;
            case 3: return 31;
            case 4: return 30;
            case 5: return 31;
            case 6: return 30;
            case 7: return 31;
            case 8: return 31;
            case 9: return 30;
            case 10: return 31;
            case 11: return 30;
            case 12: return 31;
            
            default: return 0;
                        
        
        
        }
        
        
        
    }

    private void altaCita(Date inicio,int horaCrear,Medico mm) {
        this.persistencia.iniciarTransaccion();
        try{
           
        Cita auxCita = new Cita();
        auxCita.setFecha(inicio);
        auxCita.setMedic(mm);
        auxCita.setHora(horaCrear);
        
        this.persistencia.insertar(auxCita);
        
       
        this.persistencia.confirmarTransaccion();
        
        
    }
        catch(Exception e){
           this.persistencia.descartarTransaccion();
        }
    }

    private boolean isHoyCita(int date, Medico m1) {
       
        List listado = persistencia.buscarTodosCitas(date,m1.getDni());
        
        return !listado.isEmpty();
        
    }

    private int horaMedicVieja(int i,int dni,int dia) {
        return persistencia.obtenerHoraVieja(i,dni,dia);
    }

    public List listarCitas() {
        return persistencia.buscarTodos(Cita.class);
    }

    public void reactivarEspecialidad(int codEspecialidad) 
            throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            Especialidad auxEspe = this.persistencia.buscar(Especialidad.class, codEspecialidad);
            auxEspe.setEstadoActivo(true);
            this.persistencia.modificar(auxEspe); // merger con cambio en boorado
            
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo activar la especialidad");
        }
    }

    public void bajaCita(int codCita) 
          throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            Cita auxC = this.persistencia.buscar(Cita.class, codCita);
            auxC.setEstado(false);
            this.persistencia.modificar(auxC); // merger con cambio en boorado
            
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo desactivar cita");
        }
        
    }

    public void reactivarCita(int codCita) 
            
             throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            Cita auxC = this.persistencia.buscar(Cita.class, codCita);
            auxC.setEstado(true);
            this.persistencia.modificar(auxC); // merger con cambio en boorado
            
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo reactivar cita");
        }
        
     
        
    }

    public void reactivarPaciente(int dni) 
            
                
             throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            
            Paciente auxP = this.persistencia.buscar(Paciente.class, dni);
            auxP.setEstado(true);
            this.persistencia.modificar(auxP); // merger con cambio en boorado
            
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo reactivar Paciente");
        }
        
        
        
    }

    public void borrarPaciente(int dni) 
        throws Exception{
        
        this.persistencia.iniciarTransaccion();
        try {
            
            Paciente auxP = this.persistencia.buscar(Paciente.class, dni);
            auxP.setEstado(false);
            this.persistencia.modificar(auxP); // merger con cambio en boorado
            
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo borrar Paciente");
        }
        
    
   
    }

    public boolean tieneCita(Paciente auxP) {
        //devuelve true or false depende si el paciente tiene alguna cita
      List lista =  this.persistencia.buscarCitas(auxP);
       
       return !lista.isEmpty();
        
        
    }
        
}
