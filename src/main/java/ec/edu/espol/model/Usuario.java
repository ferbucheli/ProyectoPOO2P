/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.GFG;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author davidperez
 */
public class Usuario {
    protected int id;
    protected String correo;
    protected String clave;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    
    //constructor
    
    public Usuario(int id, String correo, String clave, String nombres, String apellidos, String organizacion){
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
    }
    
    //getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }
    
    
    // funciones recuperadoras
    
    public static ArrayList<String> recuperarCorreos(String nomfile){
        ArrayList<String> correos = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                // linea = id|correo|...
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                String correo = tokens[1];
                correos.add(correo);
            }
        }
        catch(Exception e){
 
        }
        return correos;
    }
    //recuperar con correo
    public static Usuario recuperarUsuario(String correo, String nomfile){
        ArrayList<Usuario> usuarios = Usuario.recuperarUsuarios(nomfile);
        for (Usuario u: usuarios){
            if (correo.equals(u.getCorreo()))
                return u;
        }
        return null;
    }
    
    public static ArrayList<Usuario> recuperarUsuarios (String nomfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomfile))) {
             while(sc.hasNextLine()){
                // linea = id|correo|clave|nombres|apellidos|organizacion
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Usuario u = new Usuario(Integer.parseInt(tokens[0]), tokens[1], tokens [2], tokens[3], tokens[4], tokens[5]);
                usuarios.add(u);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }
    
    //comportamientos problema

    
    public static void nextUsuario(Scanner sc, String nomfile) throws NoSuchAlgorithmException{
        sc.useDelimiter("\n");
        System.out.println("REGISTRAR UN NUEVO USUARIO");
        
        String correo;
        boolean corrExis;
        boolean corrValid;
        do{
            System.out.println( "Introduzca su correo electrónico: " );
            correo = sc.next().toLowerCase();
            corrExis = correoExistente(correo, nomfile);
            corrValid = validarCorreo(correo);
            if (!corrValid)
                System.out.println("Por favor ingrese un correo válido.");
            else if (corrExis)
                System.out.println("El correo que ingresó ya posee una cuenta, por favor ingrese otro correo si desea continuar.");
        }while(!corrValid || corrExis);
        
        System.out.println( "Introduzca una clave: " );
        String clave = sc.next();
        String hashclave;
        hashclave = GFG.toHexString(GFG.getSHA(clave));
        System.out.println( "Introduzca sus nombres: " );
        String nombres = sc.next();
        System.out.println( "Introduzca sus apellidos: " );
        String apellidos = sc.next();
        System.out.println( "Introduzca su organizacion: " );
        String organizacion = sc.next();
        int id = Util.nextID(nomfile);
        Usuario u = new Usuario(id, correo, hashclave, nombres, apellidos, organizacion);
        u.saveFile(nomfile);
        System.out.println("\n");
        System.out.println("Se ha registrado su usuario con éxtio.");
        System.out.println(" -------------------------------------------------------------------------------- ");
    }
    
    
    //funcion de file
    
    public void saveFile(String nomfile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))) {
            pw.println (this.id+"|"+this.correo+"|"+this.clave+"|"+this.nombres+"|"+this.apellidos+"|"+this.organizacion);
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    /*
    public void crearArchivos() {
        File vehiculos = new File();
        
    }
    */
    
    //funciones de validacion
    
    public static boolean validarUsuario(String correo, String clave,String nomfile) throws NoSuchAlgorithmException{
        String hashclave = GFG.toHexString(GFG.getSHA(clave));
        ArrayList<Usuario> usuarios = Usuario.recuperarUsuarios(nomfile);
        for (Usuario u : usuarios){
            if (u.getCorreo().equals(correo) && u.getClave().equals(hashclave))
                return true;
        }
        return false;
    }
    
    public static boolean validarCorreo(String correo){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    
    //valida que el correo sea unico
    public static boolean correoExistente(String correo,String nomfile){
        ArrayList<String> correos = recuperarCorreos(nomfile);
        return correos.contains(correo);
    }
    
    //sobreescrituras
    @Override
    public boolean equals(Object o){
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (this.getClass() != o.getClass())
            return false;
        Usuario other = (Usuario) o;
        return (this.id == other.id);
    }
    
    @Override
    public String toString(){
        String s = "USUARIO\nNombres: " +this.nombres + "\nApellidos: " + this.apellidos+ "\nCorreo Electrónico: " + this.correo + "\nOrganización " + this.organizacion+ "\nID de usuario: " + this.id+ "";
        return s;
    }
}
