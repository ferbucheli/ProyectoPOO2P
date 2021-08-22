/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;
import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
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
    
    //menu
    
    public static void menuInicio(){
        System.out.println(" -------------------------------------------------------------------------------- ");
        System.out.println("|Bienvenido al Sistema de Compra y Venta de Vehiculos SDF /PEREZ/DECASTRO/BUCHELI|");
        System.out.println(" -------------------------------------------------------------------------------- ");
        
        System.out.println("");
        System.out.println(String.format("%50s","Menu de Opciones"+"\n" ));
        System.out.println(String.format("%45s","1. Vendedor"+"\n"));
        System.out.println(String.format("%46s","2. Comprador"+"\n"));
        System.out.println(String.format("%42s","3. Salir"+"\n"));
    }
    
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
   
    
    //extras
    
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
    
    public static void removerLinea(String nomFile, int id, int num){
        File oldFile = new File(nomFile);
        File newFile = new File("temp.txt");
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(newFile), true);
            Scanner sc = new Scanner(oldFile); 
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] tokens = line.split("\\|");
                if(Integer.parseInt(tokens[num]) != id){
                    pw.println(String.join("|", tokens));
                }
            }
            sc.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(nomFile);
            newFile.renameTo(dump);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}



