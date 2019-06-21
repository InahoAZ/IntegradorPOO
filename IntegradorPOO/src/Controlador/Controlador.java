
package Controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public void generarCitas(Date inicio, Date fin) {
       
        //cuantos dias me quedan de mes
        Date actual = new Date();
        int hoy = actual.getDate(); //num de dia de hoy
        int miMes = obtenerCantDias(actual.getMonth()+1); //entre 0 a 31
        
        int quedan = miMes - hoy; //devuelve cuantos dias de citas se generaran, comenzando desde manana
        
        
        //obtener medicos
        List <Medico> misMedicos = new ArrayList<>(listarMedicos());
       
        //para todos los dias
        if(misMedicos.size()>0){
                    
              
                    for (int i = hoy+1; i <= quedan; i++) {

                        //para cada dia
                        
                        //repetir dia para cant de medicos
                        for (int m = 0; m < misMedicos.size(); m++) {
                            Date comienza = inicio;
                             Date termina = fin;
                            //para un medico creo las citas de hoy
                            Medico m1 = misMedicos.get(m);
                            
                            //si el medico tiene tiempo por turno mayor a 0 minutos
                            if(m1.getTiempoTurno()>0){
                                //mientras se pueda crear citas para ese medico en el dia de hoy, crear
                                int tiempoxTurno = m1.getTiempoTurno(); //15 minutos ej;
                                Date tiempoMedico = inicio;
                                tiempoMedico.setHours(0);
                                tiempoMedico.setMinutes(tiempoxTurno);
                                while(comienza.compareTo(termina) < 0 ){
                                               
                                            
                                
                                            //crear cita
                                            altaCita(inicio);
                                            
                                            
                                            //suma inicio local + t turno
                                
                                
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

    private void altaCita(Date inicio) {
        this.persistencia.iniciarTransaccion();
        try{
        Cita auxCita = new Cita();
        auxCita.setFecha(inicio);
        
        this.persistencia.insertar(auxCita);
        
        this.persistencia.confirmarTransaccion();
        
        
        
    }
        catch(Exception e){
            this.persistencia.descartarTransaccion();
        }
    }
}
