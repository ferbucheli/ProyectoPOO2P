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
    
    public static int nextID(String nomfile) {
        int id = 0;
        try (Scanner sc = new Scanner (new File(nomfile))) {
            
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String [] tokens = linea.split ("\\|");
                id = Integer.parseInt(tokens[0]);
            }
            
            
        }catch(Exception e) {
            
        }
        return id+1;
    }
}



