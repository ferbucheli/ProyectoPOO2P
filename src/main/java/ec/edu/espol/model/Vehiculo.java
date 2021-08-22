/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author davidperez
 */
public class Vehiculo {

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
    
    public Vehiculo(int id, String tipo, int id_vendedor, String placa, String marca, String motor, int anio, String modelo, double recorrido, String color, String combustible, double precio, String vidrios, String transmision, String traccion){
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
    
    /* 
    Metodo para  permitirle al vendedor revisar ofertas del vehiculo;
    Puede avanzar o retroceder de oferta
    Puede aceptar la oferta
    Puede salir 
    */
    
    //comportamientos
    
    //funciones de oferta
    
    public Oferta menuOfertas(Scanner sc){
        System.out.println(this.marca + ' '+ this.modelo+ " Precio: $"+this.precio+"\n");
        if(!this.ofertas.isEmpty()){
            System.out.println("Se ha(n) realizado " + this.ofertas.size()+" oferta(s).");
            System.out.println(" -------------------------------------------------------------------------------- ");
            int i = 0;
            int opcion = 1;
            
            while(opcion >= 1 && opcion < 4){

                if (i==0) {
                    System.out.println("OFERTA #" + (i+1) + "\n" + this.ofertas.get(i)+"\n");
                    System.out.println("1. Siguiente Oferta\n2. Aceptar Oferta\n3. Salir"+"\n");
                    opcion = sc.nextInt();
                    System.out.println(" -------------------------------------------------------------------------------- ");
                    if(opcion == 1){
                        if(i == (this.ofertas.size() - 1))
                            i = 0;
                        else
                            i += 1;       
                    }   
                    else if(opcion == 2){
                        Util.removerLinea("ofertas.txt", this.ofertas.get(i).getId_Vehiculo(), 2);
                        opcion = 4;
                        return this.ofertas.get(i);
                    }
                    
                }else if (i > 0){
                    System.out.println("Oferta " + (i+1) + "\n" + this.ofertas.get(i)+"\n");
                    System.out.println("1. Siguiente Oferta\n2. Anterior Oferta\n3. Aceptar Oferta\n4. Salir"+"\n");
                    opcion = sc.nextInt();
                    System.out.println(" -------------------------------------------------------------------------------- ");
                    if(opcion == 1){
                        if(i == (this.ofertas.size() - 1))
                            i = 0;
                        else
                            i += 1;       
                        }   
                    else if(opcion == 2){
                        if(i == 0)
                            i = this.ofertas.size() - 1;
                        else
                            i -= 1;
                    }
                    else if(opcion == 3){
                        Util.removerLinea("ofertas.txt", this.ofertas.get(i).getId_Vehiculo(), 2);
                        opcion = 4;
                        return this.ofertas.get(i);
                    }
                }
            }
        }
        else{
            System.out.println("No hay ofertas para este vehiculo"+"\n");
            System.out.println(" -------------------------------------------------------------------------------- ");
        }
        return null;
    }
    
    /*
    agregarVehiculos(arreglo de strings con atributos de vehiculos, lista de vehiculos)
    Filtra por tipo de Vehiculo, crea una intancia de Vehiculo y lo añade a una lista de Vehiculos
    Es usada en la funcion readFileVehiculo
    */
    
    //funciones de nextvehiculo
    
    public static void agregarVehiculos(String[] tokens, ArrayList<Vehiculo> vehiculos){
        if(tokens[1].equals("CARRO")){
            Vehiculo v = new Vehiculo(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), tokens[7], Double.parseDouble(tokens[8]), tokens[9], tokens[10], Double.parseDouble(tokens[11]), tokens[12], tokens[13]);
            vehiculos.add(v);
        }
        else if(tokens[1].equals("MOTO")){
            Vehiculo v = new Vehiculo(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), tokens[7], Double.parseDouble(tokens[8]), tokens[9], tokens[10], Double.parseDouble(tokens[11]));
            vehiculos.add(v);
        }
        else if(tokens[1].equals("CAMIONETA")){
            Vehiculo v = new Vehiculo(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), tokens[7], Double.parseDouble(tokens[8]), tokens[9], tokens[10], Double.parseDouble(tokens[11]), tokens[12], tokens[13], tokens[14]);
            vehiculos.add(v);
        }
    }
    
    public static void nextVehiculo(Scanner sc, String nomfile, String tipo, int id_vendedor) {
        if(tipo.equals("CARRO")){
            String[] atributos = validarCarro(sc).split(",");
            int id = Util.nextID(nomfile);
            Vehiculo vehiculo = new Vehiculo(id, tipo, id_vendedor, atributos[0], atributos[1], atributos[2], Integer.parseInt(atributos[3]), atributos[4], Double.parseDouble(atributos[5]), atributos[6], atributos[7], Double.parseDouble(atributos[8]), atributos[9], atributos[10]);
            vehiculo.saveFile(nomfile);  
        }
        else if(tipo.equals("CAMIONETA")){
            String[] atributos = validarCamioneta(sc).split(",");
            int id = Util.nextID(nomfile);
            Vehiculo vehiculo = new Vehiculo(id, tipo, id_vendedor, atributos[0], atributos[1], atributos[2], Integer.parseInt(atributos[3]), atributos[4], Double.parseDouble(atributos[5]), atributos[6], atributos[7], Double.parseDouble(atributos[8]), atributos[9], atributos[10], atributos[11]);
            vehiculo.saveFile(nomfile);  
        }
        else if(tipo.equals("MOTO")){
            String[] atributos = validarAtributos(sc).split(",");
            int id = Util.nextID(nomfile);
            Vehiculo vehiculo = new Vehiculo(id, tipo, id_vendedor, atributos[0], atributos[1], atributos[2], Integer.parseInt(atributos[3]), atributos[4], Double.parseDouble(atributos[5]), atributos[6], atributos[7], Double.parseDouble(atributos[8]));
            vehiculo.saveFile(nomfile);  
        }  
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
    
    public void saveFile(String nomfile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)) ) {
            String linea = this.id + "|" + this.tipo + "|" + this.id_vendedor + "|" + this.getPlaca()+"|"+this.getMarca()+"|"+this.getMotor()+"|"+this.getAnio()+ "|" +this.getModelo()+ "|"+this.getRecorrido()+"|"+this.getColor()+"|"+this.getCombustible()+"|"+this.getPrecio();
            if(this.getTipo().equals("CARRO")) {
                pw.println( linea +"|"+this.getVidrios()+"|"+this.getTransmision() );                                        
            }else if (this.getTipo().equals("MOTO")) {
                pw.println( linea );                                        
            }else if (this.getTipo().equals("CAMIONETA")){
                pw.println( linea +"|"+this.getVidrios()+"|"+this.getTransmision()+"|"+this.getTraccion() );                                        
            } 
        }catch (Exception e){
            System.out.println(e.getMessage()+"\n");
        }
        
    } 
    
    public static ArrayList<Vehiculo> readFile(String nomfile) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomfile))) {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String [] tokens = linea.split("\\|");
                agregarVehiculos(tokens, vehiculos);
            }   
        }
        catch(Exception e) {
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