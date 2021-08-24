/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author davidperez
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
    private int id_vendedor;
    private Vendedor vendedor;
    private ArrayList<Oferta> ofertas;
    private static final long serialVersionUID = 93423423423423234L;
    
    // Constructor de Autos
 
    public Vehiculo(int id, String tipo, int id_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision){
        this.id = id;
        this.tipo = tipo;
        this.id_vendedor = id_vendedor;
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
        this.ofertas = new ArrayList<>();
    }
    
    // Constructor de Camionetas
    
    public Vehiculo(String tipo, int id_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision, String traccion){
        //this.id = id;
        this.tipo = tipo;
        this.id_vendedor = id_vendedor;
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
        this.traccion = traccion;
        this.ofertas = new ArrayList<>();
    }
    
    // Constructor de motos
    
    public Vehiculo(int id, String tipo, int id_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio){
        this.id = id;
        this.tipo = tipo;
        this.id_vendedor = id_vendedor;
        this.placa = placa;
        this.marca = marca;
        this.motor = motor;
        this.anio = anio;
        this.modelo = modelo;
        this.recorrido = recorrido;
        this.color = color;
        this.combustible = combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
    }

    //getters y setters

    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    } 
    
    //validaciones
    
    public static String validarCarro(Scanner sc){      
        String atributos = Vehiculo.validarAtributos(sc);             
        System.out.println("Ingrese el tipo de vidrio del vehiculo: ");
        String vidrios = sc.next().toUpperCase();    
        System.out.println("Ingrese la transmision del vehiculo: ");
        String transmision = sc.next().toUpperCase();     
        return atributos + "," + vidrios + "," + transmision;
    }

    public static String validarCamioneta(Scanner sc){
        String atributos = Vehiculo.validarAtributos(sc);
        System.out.println("Ingrese el tipo de vidrio del vehiculo: ");
        String vidrios = sc.next().toUpperCase();    
        System.out.println("Ingrese la transmision del vehiculo: ");
        String transmision = sc.next().toUpperCase();
        System.out.println("Ingrese el tipo de traccion del vehiculo: ");
        String traccion = sc.next().toUpperCase();
        return atributos + "," + vidrios + "," + transmision + "," + traccion;
    }
    
    public static String validarAtributos(Scanner sc){
        System.out.println("Ingrese la placa del vehiculo: ");
        String placa = Vehiculo.recuperarPlaca(sc.next(), sc);          
        System.out.println("Ingrese la marca del vehiculo: ");
        String marca = sc.next().toUpperCase();             
        System.out.println("Ingrese el modelo del vehiculo: ");
        String modelo = sc.next().toUpperCase();                 
        System.out.println("Ingrese el tipo de motor del vehiculo: ");
        String motor = sc.next().toUpperCase();       
        System.out.println("Ingrese el año del vehiculo: ");
        int anio = sc.nextInt();
        double recorrido;
        do{
        System.out.println("Ingrese el recorrido que tiene el vehiculo: ");
        recorrido = sc.nextInt();
        }while (recorrido < 0);
        System.out.println("Ingrese el color del vehiculo: ");
        String color = sc.next().toUpperCase();
        String combustible;
        do{
        System.out.println("Ingrese el tipo de combustible del vehiculo (SUPER, EXTRA, ECOPAIS, DIESEL): ");
        combustible = sc.next().toUpperCase();        
        }while( ( !combustible.equals("SUPER")  ) && ( !combustible.equals("EXTRA") ) && ( !combustible.equals("ECOPAIS")) && ( !combustible.equals("DIESEL")));
        int precio;
        do{
        System.out.println("Ingrese el precio del vehiculo: ");
        precio = sc.nextInt();      
        }while (precio < 0 );
        return placa + "," + marca + "," + motor + "," + anio + "," + modelo + "," + recorrido + "," + color + "," + combustible + "," + precio;
    }
    
    public static boolean validarPlaca(String placa){
        String regex = "^[A-Z][A-Z][A-Z]-[0-9]{3,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();
    }
    
    
    //funciones de file
    
    public static void saveFile(String nomFile, ArrayList<Vehiculo> vehiculos) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFile))){
            out.writeObject(vehiculos);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
         
    
    public static ArrayList<Vehiculo> readFile(String nomfile) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(ObjectInputStream out = new ObjectInputStream(new FileInputStream(nomfile))){
            vehiculos = (ArrayList<Vehiculo>)out.readObject();
        } catch(IOException e){
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }
    

    // Borra el vehiculo de la base de datos
    public void borrarVehiculo(){
        Util.removerLinea("vehiculos.txt", this.id,0);
    }
    
    //funciones link
    
    public static ArrayList<Vehiculo> linkVehiculo(String nomFile, int id_vendedor){
        ArrayList<Vehiculo> vehiculos = readFile(nomFile);
        ArrayList<Vehiculo> vehiculos2 = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.getId_vendedor() == id_vendedor){
                vehiculos2.add(v);
            }
        }
        return vehiculos2;
    }
    

    public static void linkOfertas(ArrayList<Vehiculo> vehiculos) {
        ArrayList<Oferta> ofertas = Oferta.readFile("ofertas.txt");
        for (Vehiculo v : vehiculos){
            for (Oferta o : ofertas) {
                if (v.id == o.getId_Vehiculo() ) {
                    v.getOfertas().add(o);
                    o.setVehiculo(v);
                }
            }

        }
    }
    //funcion recuperar
    
    public static String recuperarPlaca(String placa,Scanner sc) {
        
        boolean placaVal = Vehiculo.validarPlaca(placa);
        boolean placaExis = Vehiculo.searchByPlaca(Vehiculo.readFile("vehiculos.txt"), placa) != null;
        while(!placaVal|| placaExis){
            if(!placaVal)
                System.out.println("ERROR! Ingrese una placa valida"+"\n");
            else if (placaExis){
                System.out.println("Este vehiculo ya esta registrado en el sistema! Por favor ingrese de nuevo"+"\n");
            }
            placa = sc.next();
            placaVal = Vehiculo.validarPlaca(placa);
            placaExis = Vehiculo.searchByPlaca(Vehiculo.readFile("vehiculos.txt"), placa) != null;
        }
        
        return placa;
        
    }
    
    //funciones searchby
    
    // Busca un Vehiculo por id
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int id){
        
        for(Vehiculo v : vehiculos){
            if(v.id == id)
                return v;
        }
        return null;
    }
    
    // Busca un Vehiculo por placa
    public static Vehiculo searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        
        for(Vehiculo v : vehiculos){
            if(v.placa.equals(placa))
                return v;
        }
        return null;
    }
    
    public static ArrayList<Vehiculo> searchByTipo(String tipo, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byTipo = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(tipo.equals(v.getTipo()))
                byTipo.add(v);
        }
        return byTipo;
    }
    
    public static ArrayList<Vehiculo> searchByRecorrido(double rmin, double rmax, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byRecorrido = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.recorrido >= rmin && v.recorrido <= rmax)
                byRecorrido.add(v);
        }
        return byRecorrido;
    }
    
    public static ArrayList<Vehiculo> searchByPrecio(double pmin, double pmax, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byPrecio = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.precio >= pmin && v.precio <= pmax)
                byPrecio.add(v);
        }
        return byPrecio;
    }
    
    public static ArrayList<Vehiculo> searchByAnio(int amin, int amax, ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> byAnio = new ArrayList<>();
        for(Vehiculo v : vehiculos){
            if(v.anio >= amin && v.anio <= amax)
                byAnio.add(v);
        }
        return byAnio;
    }
    
    //sobreescrituras
    @Override
    public String toString() {
        return "Tipo: " + this.tipo + "\nPlaca: " + placa + "\nMarca: " + marca + "\nMotor: " + motor + "\nAño: " + anio + "\nModelo: " + modelo + "\nRecorrido: " + recorrido + "\nColor: " + color + "\nCombustible: " + combustible + "\nPrecio: " + precio;
             
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(o.getClass() != this.getClass())
            return false;
        
        Vehiculo other = (Vehiculo)o;
        
        return Objects.equals(this.id, other.id);   
    }
}