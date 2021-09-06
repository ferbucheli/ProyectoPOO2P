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
        try {
            this.clave = GFG.toHexString(GFG.getSHA(clave));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.rol = rol;
        vehiculos = new ArrayList<Vehiculo>();
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
            ArrayList<Usuario> users = new ArrayList<>();
            Usuario.guardarUsuarios("usuarios.ser", users);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return usuarios;
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
    
    public static ArrayList<Usuario> actualizarClave(ArrayList<Usuario> usuarios, Usuario usuario){
        ArrayList<Usuario> usuariosA = usuarios;
        for(Usuario u : usuariosA){
            if(u.getCorreo().equals(usuario.getCorreo())){
                u.setClave(usuario.getClave());
            }
        }
        return usuariosA;
    }
    
    public static ArrayList<Usuario> actualizarRol(ArrayList<Usuario> usuarios, Usuario usuario){
        ArrayList<Usuario> usuariosA = usuarios;
        for(Usuario u : usuariosA){
            if(u.getCorreo().equals(usuario.getCorreo())){
                u.setRol(usuario.getRol());
            }
        }
        return usuariosA;
    }
    
    public static boolean validarCorreo(ArrayList<Usuario> users,String mail){
        System.out.println(users);
        for(Usuario u: users){
            if(u.getCorreo().equals(mail)){
                return false;
            }
        }
        return true;
    }
    
    public static String obtenerClave(String clave1){
        String clave2 = null;
        try {
            clave2 = GFG.toHexString(GFG.getSHA(clave1));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return clave2;
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
    
    
    public static Usuario extraerUsuario(int ID,ArrayList<Usuario> usuarios){
        
        for(Usuario U:usuarios){
            if(U.getId()==ID)
                return U;

        }
        return null;
    }
    
    
    
}


