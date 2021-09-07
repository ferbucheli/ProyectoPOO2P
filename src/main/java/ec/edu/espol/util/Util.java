/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author davidperez
 */
public class Util {
    
    private Util(){}
    
    public static int nextIDUsuario(ArrayList<Usuario> usuarios) {
        int id = 0;
        for(Usuario u : usuarios){
            id = u.getId();
        }
        return id+1;
    }
    
    public static int nextIDVehiculo(ArrayList<Vehiculo> vehiculos){
        int id = 0;
        for(Vehiculo v : vehiculos){
            id = v.getId();
        }
        return id+1;
    }
    
    public static int nextIDOferta(ArrayList<Oferta> ofertas){
        int id = 0;
        for(Oferta o : ofertas){
            id = o.getId();
        }
        return id+1;
    }
    
    public static void actualizar(ArrayList<Usuario> usuarios, String nomfile){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(usuarios);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
        public static void sendMail(String recipient){
        Properties properties=new Properties();
        
        String cuenta="";
        String pass="";
        try (InputStream inputStream = new FileInputStream("info.properties")) {
                Properties prop = new Properties();
                prop.load(inputStream);
                System.out.println("\tLeemos el valor de las claves");
                // get value by key
                cuenta=(prop.getProperty("db.usuario"));
                
                pass=(prop.getProperty("db.password"));
            } catch (FileNotFoundException ex) {            
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
             
            
        
        
        
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String miCuenta=cuenta;
        String password=pass;
        
        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(miCuenta,password);
            }
        
        });
        
        Message message = prepareMessage(session,miCuenta,recipient);
        
        try {
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        System.out.println("Mensaje enviado con exito");
        
    
    }
    
    private static Message prepareMessage(Session session,String miCuenta,String recipient){
    
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(miCuenta));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Respuesta Oferta");
            message.setText("Su oferta ha sido aceptada");
            return message;
            
            
        } catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return null;
    
    }

}



