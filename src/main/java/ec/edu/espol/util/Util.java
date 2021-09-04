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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
}



