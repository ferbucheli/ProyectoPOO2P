/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.exceptions.PlacaException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jara-Cadena
 */
public class Vehiculo implements Serializable{
    
    private int id;
    private String tipo;
    private String placa;
    private String marca;
    private String motor;
    private int anio;
    private String modelo;
    private double recorrido;
    private String color; 
    private String combustible; 
    private double precio;  
    private String vidrios;  
    private String transmision;
    private String traccion; 
    private int id_usuario;
    private Usuario usuario;
    private ArrayList<Oferta> ofertas;
    private String rutaImg;
    private static final long serialVersionUID = 93423423423423234L;
    
    // Constructor de Autos
 
    public Vehiculo(int id, String tipo, int id_usuario, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision, String rutaImg) throws PlacaException{
        this.id = id;
        this.tipo = tipo;
        this.id_usuario = id_usuario;
        if(validarPlaca(placa))
            this.placa = placa;
        else
            throw new PlacaException("La placa esta en un formato no valido!");
        this.placa = placa;
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.rutaImg = rutaImg;
        this.ofertas = new ArrayList<>();
    }
    
    // Constructor de Camionetas
    
    public Vehiculo(int id, String tipo, int id_usuario, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision, String traccion, String rutaImg) throws PlacaException{
        this.id = id;
        this.tipo = tipo;
        this.id_usuario = id_usuario;
        if(validarPlaca(placa))
            this.placa = placa;
        else
            throw new PlacaException("La placa esta en un formato no valido!");
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.traccion = traccion;
        this.rutaImg = rutaImg;
        this.ofertas = new ArrayList<>();
    }
    
    // Constructor de motos
    
    public Vehiculo(int id, String tipo, int id_usuario, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String rutaImg) throws PlacaException{
        this.id = id;
        this.tipo = tipo;
        this.id_usuario = id_usuario;
        if(validarPlaca(placa))
            this.placa = placa;
        else
            throw new PlacaException("La placa esta en un formato no valido!");
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.rutaImg = rutaImg;
        this.ofertas = new ArrayList<>();
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getMotor() {
        return motor;
    }

    public int getAnio() {
        return anio;
    }

    public String getModelo() {
        return modelo;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public String getColor() {
        return color;
    }

    public String getCombustible() {
        return combustible;
    }

    public double getPrecio() {
        return precio;
    }

    public String getVidrios() {
        return vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public String getTraccion() {
        return traccion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public Usuario getVendedor() {
        return usuario;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public void setId_usuario(int id_vendedor) {
        this.id_usuario = id_vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.usuario = vendedor;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    
    public static void guardarVehiculo(String nomFile,ArrayList<Vehiculo> Vehiculos){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFile))) {
            out.writeObject(Vehiculos);
            out.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<Vehiculo> cargarVehiculos(String nombreArchivo){
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        try (ObjectInputStream out = new ObjectInputStream(new FileInputStream(nombreArchivo));) {
            listaVehiculos = (ArrayList<Vehiculo>)out.readObject();  
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return listaVehiculos;
    } 
    
    public static ArrayList<Vehiculo> extraerVehiculos(ArrayList<Usuario> usuarios){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        for(Usuario u : usuarios){
            for(Vehiculo v : u.getVehiculos()){
                vehiculos.add(v);
            }
        }
        return vehiculos;
    }
    
    public static void agregarVehiculo(ArrayList<Usuario> usuarios, Usuario usuario, Vehiculo vehiculo){
        for(Usuario u : usuarios){
            if(u.getCorreo().equals(usuario.getCorreo())){
                u.getVehiculos().add(vehiculo);
            }
        }
    }
    
    public static boolean validarPlaca(String placa){
        String regex = "^[A-Z][A-Z][A-Z]-[0-9]{3,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }
    
    
    public static ArrayList<Usuario> borrarVehiculo(ArrayList<Usuario> usuarios, String placa){
        ArrayList<Usuario> usuariosa = usuarios;
        for(Usuario u : usuariosa){
            for(int i = 0; i < u.getVehiculos().size(); i++){
                if(u.getVehiculos().get(i).getPlaca().equals(placa))
                    u.getVehiculos().remove(u.getVehiculos().get(i));
            }
        }
        return usuariosa;
    }
    
    public void borrarOferta(Oferta o){
        this.ofertas.remove(o);
    }
    
    public static Vehiculo extraerVehiculo(ArrayList<Usuario> usuarios, String placa){
        for(Usuario u : usuarios){
            for(Vehiculo v : u.getVehiculos()){
                if(v.placa.equals(placa))
                    return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", marca=" + marca + ", motor=" + motor + ", modelo=" + modelo + '}';
    }
     
    
    
}
