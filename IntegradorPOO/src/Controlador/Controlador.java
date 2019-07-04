
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
import modelo.Registro;


public class Controlador {
    Persistencia persistencia ;
    
    public Controlador(Persistencia p){
        this.persistencia = p ;
    }
    
    public List listarMedicos(){
       return persistencia.buscarTodos(Medico.class);
      
    }
    
    public void agregarEspecialidadMedico(Medico m, Especialidad e) {
        // como uso un set no necesito controlar por duplicados
        // recuerden que set no permite duplicados
        this.persistencia.iniciarTransaccion();
        m.agregarEspecialidad(e);
        this.persistencia.modificar(e);
        this.persistencia.modificar(m);
        this.persistencia.confirmarTransaccion();
    }

    public void quitarEspecialidadMedico(Medico m, Especialidad e) {
        
        this.persistencia.iniciarTransaccion();
        try{
        m.eliminarEspecialidad(e);
        this.persistencia.modificar(e);
        this.persistencia.modificar(m);
        this.persistencia.confirmarTransaccion();
        System.out.println("Confirma3");
        }catch(Exception exc){
            System.out.println(exc.getMessage());
            this.persistencia.descartarTransaccion();
        }
    }
    
    public void altaMedico(String dni, String apellido, String nombre, String telefono, int tiempoTurno, ListModel esp )
        throws Exception{
        
        try{
            this.persistencia.iniciarTransaccion();
    
            int dniInt = Integer.parseInt(dni);
            int telfInt = Integer.parseInt(telefono);            
            Medico auxMed = new Medico(dniInt);            
            System.out.println(dniInt);
            for (int i = 0; i < esp.getSize(); i++) {  //Asocia todas las especialidades de la lista al medico.                
                auxMed.agregarEspecialidad((Especialidad)esp.getElementAt(i));                
            }
            //Especialidad esss = (Especialidad)esp.getElementAt(0);
            //System.out.println(esss.getClass());
            //Especialidad esss = new Especialidad("Coso","Cosito");
                        
                      
            auxMed.setApellido(apellido);
            auxMed.setNombre(nombre);
            auxMed.setTelefono(telfInt);
            auxMed.setTiempoTurno(tiempoTurno);
            
            //this.persistencia.modificar(auxMed);
            this.persistencia.modificar((Especialidad)esp.getElementAt(0));
            
            this.persistencia.insertar(auxMed);
            //uxMed.setEspecialidades((Especialidad) esp.getElementAt(0));
            this.persistencia.confirmarTransaccion();

        } catch (Exception e){
            System.out.println(e.getMessage());
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo cargar el Medico");

}
        }
    public void modificarMedico(int dni, String apellido, String nombre, String telefono, int tTurno,Medico m)
    throws Exception{
        this.persistencia.iniciarTransaccion();

        try{
            m.setNombre(nombre);
            m.setApellido(apellido);
            m.setTelefono(Integer.parseInt(telefono));
            m.setTiempoTurno(tTurno);     
            
            this.persistencia.modificar(m);
            this.persistencia.confirmarTransaccion();
        }catch(Exception e){
            System.out.println(e.getMessage());
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo modificar el Medico");
        }
        
    
    
    
    
    }
    
    public void addRegistro(String diagnostico, Cita cita){
    
        this.persistencia.iniciarTransaccion();
        try{
            HistoriaClinica hc = cita.getElpaciente().getHistoriaC();            
            hc.nuevoRegistro(diagnostico, cita.getMedic());            
            this.persistencia.modificar(hc);
            System.out.println("Se inserto Registro");
            this.persistencia.confirmarTransaccion();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.err.println("No se pudo Cargar el Medico");
            this.persistencia.descartarTransaccion();
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
            /*Medico auxMed = this.persistencia.buscar(Medico.class, m.getDni());
            auxMed.setActivo(false);
            this.persistencia.modificar(m);*/
            if ((m.getCitas().isEmpty())) {
                this.persistencia.eliminar(m);
                this.persistencia.confirmarTransaccion();
            }else if(m.getEspecialidades().isEmpty()){
                m.getEspecialidades().forEach((espe) -> {
                    m.eliminarEspecialidad(espe);
                    this.persistencia.modificar(m);
                    this.persistencia.eliminar(m);
                    this.persistencia.confirmarTransaccion();
                });
                
            }else{
                System.out.println("Por favor reasigne las citas del medico a eliminar");
            }
            
            
        
        }catch(Exception e){
            this.persistencia.descartarTransaccion();
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
                                                 horaInicioViejo =  horaMedicVieja(1,m1,diaHoy.getDate());//pasar el dia de hoy
                                                 horaFinalViejo =  horaMedicVieja(2,m1,diaHoy.getDate());//pasar el dia de hoy
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
       
        //me retorna la lisata de citas para hoy de juancho
        
        //List listado = persistencia.buscarTodosCitas(date,m1.getDni());
        List listado = m1.getCitasHoy(date);
        
        return !listado.isEmpty();
        
    }

    private int horaMedicVieja(int i,Medico mni,int dia) {
      
        int hora = 0;
        if(i==1){
            hora = mni.getHoraMin(dia);
            return hora;
            
        }else{
            hora = mni.getHoraMax(dia);
            return hora;
        }
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

    public void asignarCita(Paciente pac, Cita cita) {
       
                
        this.persistencia.iniciarTransaccion();
        try {
 
             cita.setElpaciente(pac);
             pac.setMiCita(cita);
     
            this.persistencia.modificar(cita);
            this.persistencia.modificar(pac);
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo asignar la cita");
        }
        
        
    }

    public void eliminarCita(Paciente pac) {
        
        this.persistencia.iniciarTransaccion();
        try {
            Cita cita = pac.getMiCita();
             cita.setElpaciente(null);
             pac.setMiCita(null);
     
            this.persistencia.modificar(cita);
            this.persistencia.modificar(pac);
            
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo descartar la cita");
        }
        
        
        
        
    }

    public List listarCitasAsistidas(Paciente paci) {
        List citas = persistencia.buscarTodos(Cita.class);
        List citasP = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita c1 = (Cita) citas.get(i);
            if(c1.getElpaciente() == paci){
                citasP.add(c1);
            
            }
            
            
            
        }
        
        List asistido = new ArrayList<>();
        for (int i = 0; i < citasP.size(); i++) {
            Cita cc1 = (Cita) citas.get(i);
            if(cc1.isAsistido()){
            
                asistido.add(cc1);
            
            }
        }
        return asistido;
    }

    public List listarCitasFaltas(Paciente paci) {
      
        
        
        List citas = persistencia.buscarTodos(Cita.class);
        List citasP = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita c1 = (Cita) citas.get(i);
            if(c1.getElpaciente() == paci){
                citasP.add(c1);
            
            }
            
            
            
        }
        
        List faltadas = new ArrayList<>();
        for (int i = 0; i < citasP.size(); i++) {
            Cita cc1 = (Cita) citas.get(i);
            if(!cc1.isAsistido() && cc1.getElpaciente() == paci){
            
                faltadas.add(cc1);
                System.out.println(cc1);
                
            
            }
        }
        return faltadas;
        
        
    }

    public List listarCitasNoRecor() {
        
        List citas = persistencia.buscarTodos(Cita.class);
        List citasRecor = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita cc1 = (Cita) citas.get(i);
            if(!cc1.isRecordado() && cc1.getElpaciente() != null){
                    
                    citasRecor.add(cc1);
            
            }
        }
        
        return citasRecor;
    }

    public List listarCitasSiRecor() {
        
        
         List citas = persistencia.buscarTodos(Cita.class);
        List citasRecor = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            Cita cc1 = (Cita) citas.get(i);
            if(cc1.isRecordado()){
                    
                    citasRecor.add(cc1);
            
            }
        }
        
        return citasRecor;
        
        
    }

    public void recordarCita(Cita cc) {
        
            this.persistencia.iniciarTransaccion();
        try {   
            cc.setRecordado(true);
            this.persistencia.modificar(cc);
            this.persistencia.confirmarTransaccion();
        } catch (Exception e) {
            this.persistencia.descartarTransaccion();
            System.err.println("No se pudo recordar la cita");
        }
        }
    
    public void finalCita(Cita cita, Paciente auxPaciente) {
            cita.setAsistido(true);
            auxPaciente.setMiCita(null);
    }
}
