/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jara-Cadena
 */
public class Vehiculo implements Serializable{
    
        private String Placa;
	private String Marca;
	private String Modelo;
	private double tipomotor;
	private int Año;
	private Double Recorrido;
	private String Color;
	private String Tipocombustible;
	private int Vidrios;
	private String Transmision;
	private String Traccion;
	private double Precio;
        private String TipoVehiculo;

    public String getMarca() {
        return Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public double getTipomotor() {
        return tipomotor;
    }

    public int getAño() {
        return Año;
    }

    public Double getRecorrido() {
        return Recorrido;
    }

    public String getColor() {
        return Color;
    }

    public String getTipocombustible() {
        return Tipocombustible;
    }

    public int getVidrios() {
        return Vidrios;
    }

    public String getTransmision() {
        return Transmision;
    }

    public String getTraccion() {
        return Traccion;
    }

    public double getPrecio() {
        return Precio;
    }

    public String getTipoVehiculo() {
        return TipoVehiculo;
    }

        
        
   
   
    public Vehiculo(String TipoVehiculo,String Placa,String Marca,String Modelo,double tipomotor,int Año,double Recorrido,String Color,String Tipocombustible,double Precio){
        this.Placa=Placa;
        this.Marca=Marca;
        this.Modelo=Modelo;
        this.tipomotor=tipomotor;
        this.Año=Año;
        this.Recorrido=Recorrido;
        this.Color=Color;
        this.Tipocombustible=Tipocombustible;
        this.Precio=Precio;
        this.TipoVehiculo=TipoVehiculo;
 
    
    }
    
    
     public Vehiculo(String TipoVehiculo,String Placa,String Marca,String Modelo,double tipomotor,int Año,double Recorrido,String Color,String Tipocombustible,int Vidrios,String Transmision,double Precio){
        this.Placa=Placa;
        this.Marca=Marca;
        this.Modelo=Modelo;
        this.tipomotor=tipomotor;
        this.Año=Año;
        this.Recorrido=Recorrido;
        this.Color=Color;
        this.Tipocombustible=Tipocombustible;
        this.Precio=Precio;
        this.Vidrios=Vidrios;
        this.Transmision=Transmision;
        this.TipoVehiculo=TipoVehiculo;
        
    }
    
        
    public Vehiculo(String TipoVehiculo,String Placa,String Marca,String Modelo,double tipomotor,int Año,double Recorrido,String Color,String Tipocombustible,String Traccion,int Vidrios,String Transmision,double Precio){
        this.Placa=Placa;
        this.Marca=Marca;
        this.Modelo=Modelo;
        this.tipomotor=tipomotor;
        this.Año=Año;
        this.Recorrido=Recorrido;
        this.Color=Color;
        this.Tipocombustible=Tipocombustible;
        this.Precio=Precio;
        this.Vidrios=Vidrios;
        this.Transmision=Transmision;
        this.Traccion=Traccion;
        this.TipoVehiculo=TipoVehiculo;
      
    
    
    }

    public String getPlaca() {
        return Placa;
    }
    
    
    
    public static void guardarVehiculo(String nombreArchivo,ArrayList<Vehiculo> Vehiculos){
        
         
        try {
            FileOutputStream fous = new FileOutputStream(nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(fous);
            out.writeObject(Vehiculos);
            out.flush();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
       
    
    }
    
    
    
         
    
    public static ArrayList<Vehiculo> cargarVehiculo(String nombreArchivo){
        
        
        try {
            FileInputStream fous = new FileInputStream(nombreArchivo);
             ObjectInputStream out = new ObjectInputStream(fous);
            ArrayList<Vehiculo> ListaVehiculo= (ArrayList<Vehiculo>)out.readObject();
            return ListaVehiculo;
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
           
            
       
    
        return null;
    } 
    
    
    
    
     
}
