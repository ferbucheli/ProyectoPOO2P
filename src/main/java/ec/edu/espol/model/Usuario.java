/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.GFG;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author davidperez
 */
public class Usuario implements Serializable{
    protected int id;
    protected String correo;
    protected String clave;
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String rol;
    protected ArrayList<Vehiculo> vehiculos;
    protected ArrayList<Oferta> ofertas;
    private static final long serialVersionUID = 93423423423423234L;
    
    //constructores
    
    // Constructor de ambos roles
    public Usuario(int id, String correo, String clave, String nombres, String apellidos, String organizacion, String rol){
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.rol = rol;
        vehiculos = new ArrayList<>();
        ofertas = new ArrayList<>();
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

    public String getRol() {
        return rol;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
         
    
    public static void saveFile(String nomFile, ArrayList<Vehiculo> vehiculos) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFile))){
            out.writeObject(vehiculos);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
         
    
    public static ArrayList<Usuario> readFile(String nomfile) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream out = new ObjectInputStream(new FileInputStream(nomfile))){
            usuarios = (ArrayList<Usuario>)out.readObject();
        } catch(IOException e){
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }
    
    public ArrayList<String> informacionUsuario(){
        ArrayList<String> info = new ArrayList<>();
        info.add(this.nombres);
        info.add(this.apellidos);
        info.add(this.organizacion);
        info.add(this.correo);
        info.add(this.rol);
        return info;
    }
    
    // funciones recuperadoras
    
    public static void guardarUsuarios(String nomFile,ArrayList<Usuario> usuarios){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFile))) {
            out.writeObject(usuarios);
            out.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<Usuario> cargarUsuarios(String nombreArchivo){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(nombreArchivo));) {
            usuarios = (ArrayList<Usuario>)out.readObject();  
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return usuarios;
    } 
    
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
    /*
    public static Usuario recuperarUsuario(String correo, String nomfile){
        ArrayList<Usuario> usuarios = Usuario.recuperarUsuarios(nomfile);
        for (Usuario u: usuarios){
            if (correo.equals(u.getCorreo()))
                return u;
        }
        return null;
    }
    
    
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
    */
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
    
    public static Usuario extraerUsuario(ArrayList<Usuario> usuarios, String correo){
        for(Usuario u : usuarios){
            if(Objects.equals(u.getCorreo(), correo))
                return u;
        }
        return null;
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
        String s = "USUARIO\nNombres: " +this.nombres + "\nApellidos: " + this.apellidos+ "\nCorreo Electrónico: " + this.correo + "\nRol: " + this.rol;
        return s;
    }
    
   
}
