/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author davidperez
 */
public class Oferta implements Serializable, Comparable<Oferta> {
    private int id;
    private int id_Comprador;
    private int id_Vehiculo;
    private double precio_ofertado;
    private Vehiculo vehiculo;
    private Usuario usuario;
    private String correo_comprador;
    private String placa;
    private static final long serialVersionUID = 93423423423423234L;
    
    //constructor

    public Oferta(int id, int id_Comprador, int id_Vehiculo, double precio_ofertado, String correo_comprador,String placa) {
        this.id = id;
        this.id_Comprador = id_Comprador;
        this.id_Vehiculo = id_Vehiculo;
        this.precio_ofertado = precio_ofertado;
        this.correo_comprador = correo_comprador;
        this.placa=placa;
    }
    
    //getters y setters

    public String getPlaca() {
        return placa;
    }

    public int getId() {
        return id;
    }

    public int getId_Comprador() {
        return id_Comprador;
    }

    public int getId_Vehiculo() {
        return id_Vehiculo;
    }

    public double getPrecio_ofertado() {
        return precio_ofertado;
    }

    public String getVehiculo() {
        return vehiculo.getPlaca();
    }

    public String getCorreo_comprador() {
        return correo_comprador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_Comprador(int id_Comprador) {
        this.id_Comprador = id_Comprador;
    }

    public void setId_Vehiculo(int id_Vehiculo) {
        this.id_Vehiculo = id_Vehiculo;
    }

    public void setPrecio_ofertado(double precio_ofertado) {
        this.precio_ofertado = precio_ofertado;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setCorreo_comprador(String correo_comprador) {
        this.correo_comprador = correo_comprador;
    }
    
    public static ArrayList<Usuario> borrarOfertas(ArrayList<Usuario> usuarios, String placa){
        ArrayList<Usuario> usuariosa = usuarios;
        for(Usuario u : usuariosa){
            for(int i = 0; i < u.getOfertas().size(); i++){
                if(u.getOfertas().get(i).getPlaca().equals(placa))
                    u.getOfertas().remove(u.getOfertas().get(i));
            }
        }
        return usuariosa;
    }
    
    //funciones de file
    
    //sobreescrituras
    
    @Override
    public String toString() {
        return "Correo comprador: " + this.correo_comprador + "\nPrecio Ofertado: " + this.precio_ofertado;
    }

    @Override
    public int compareTo(Oferta o) {
        return (int)this.precio_ofertado - (int)o.precio_ofertado;
    }

}
