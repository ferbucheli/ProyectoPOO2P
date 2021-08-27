/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.Scanner;
import ec.edu.espol.util.Util;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;

/**
 *
 * @author davidperez
 */
public class Vendedor extends Usuario implements Serializable{
    
    private ArrayList<Vehiculo> vehiculos;
    private static final long serialVersionUID = 93423423423423234L;

    //Constructor
    
    public Vendedor ( int id,String correo, String clave, String nombres, String apellidos, String organizacion, String rol) {
        super(id,correo,clave,nombres,apellidos,organizacion,rol);
        this.vehiculos = new ArrayList<>();
    }
    
    public Vendedor(Usuario u){
        super(u.getId(), u.getCorreo(), u.getClave(), u.getNombres(),u.getApellidos(),u.getOrganizacion(),u.rol);
        this.vehiculos = new ArrayList<>();   
    }
    
    //getters y setters

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    //comportamientos

    /*
    public void ingresarVehiculo(Scanner sc,String nomfile) {
        
        System.out.println("Ingrese el tipo de Vehiculo (MOTO, CARRO, CAMIONETA):\n");
        String tipo = sc.next().toUpperCase();
        
        while ( (!tipo.equals("MOTO")  )&&((!tipo.equals("CARRO"))&&((!tipo.equals("CAMIONETA"))  ))) {
            System.out.println("ERROR! Ingrese un tipo valido"+"\n");
            tipo = sc.next().toUpperCase();   
        }
        
        Vehiculo.nextVehiculo(sc, nomfile, tipo, this.id);
        System.out.println("\n");
        System.out.println("Su vehiculo se ha ingresado exitosamente al sistema.\n");
        System.out.println(" -------------------------------------------------------------------------------- ");
        
    }
    
    public void verOfertas (Scanner sc) {
        
        System.out.println("Ingrese la placa del vehiculo: ");
        String placa = sc.next();
        
        while (!Vehiculo.validarPlaca(placa)) {
            System.out.println("ERROR! Placa incorrecta, ingrese una placa valida: ");
            placa = sc.next();
        }
        
        this.vehiculos = Vehiculo.linkVehiculo("vehiculos.txt", this.id);
        Vehiculo.linkOfertas(vehiculos);
        for (Vehiculo v : this.vehiculos) {
            if (v.getPlaca().equals(placa)) {
                Oferta o = v.menuOfertas(sc);
                if(o != null){
                    // funcion de mandar email y necesito el correo del comprador.
                    // Vendedor.enviarCorreo(o.getCorreo_comprador(),v.getMarca(),v.getModelo(),v.getMotor(),o.getPrecio_ofertado(),v.getPlaca());
                    //borrar en base de datos
                    v.borrarVehiculo();

                }
            }
        }  
    }
    
    //extras 
    
    /*
    public static void enviarCorreo(String destinatario, String marca, String modelo,String motor, double dinero, String placa) {

        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        //props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        
        props.put("mail.smtp.user", "sistema.sdf.poo@gmail.com");
        props.put("mail.smtp.clave", "ProyectoPOO");    //La clave de la cuenta


        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject("Oferta aceptada");
            message.setText("Un gusto le saluda el sistema de la app SDF. Se ha aceptado su oferta de $"+dinero+" por el vehiculo "+marca+" "+modelo+" "+motor+" con la placa: "+placa);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "sistema.sdf.poo@gmail.com", "ProyectoPOO");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Se ha aceptado la oferta exitosamente y se ha notificado al comprador de su vehículo.");
            System.out.println(" -------------------------------------------------------------------------------- ");
        }
        catch (Exception e) {
            e.printStackTrace();   //Si se produce un error
        }
    }

    
    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int id){
        for(Vendedor v : vendedores){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }
    
    public static int menuVendedor(Scanner sc){
        
        
        System.out.println("1. Ingresar nuevo vendedor\n2. Registrar un vehiculo\n3. Aceptar Ofertas\n4. Regresar"+"\n");
        int opcion = sc.nextInt();
        
        System.out.println(" -------------------------------------------------------------------------------- ");
        return opcion;
    }
    
    
    public static Vendedor inicioSesionV(Scanner sc) throws NoSuchAlgorithmException{
        String correo;
        String clave;

        do{
            System.out.println( "Introduzca su correo electrónico: " );
            correo = sc.next().toLowerCase();
            System.out.println( "Introduzca su clave: " );
            clave = sc.next();
        }while(!Usuario.validarUsuario(correo,clave,"vendedores.txt"));
        Vendedor u = new Vendedor(Usuario.recuperarUsuario(correo, "vendedores.txt"));
        System.out.println(" -------------------------------------------------------------------------------- ");
        return u;
    }
    */
}
