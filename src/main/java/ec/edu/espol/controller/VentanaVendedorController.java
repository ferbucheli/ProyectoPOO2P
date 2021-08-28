/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class VentanaVendedorController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Text txt1;

    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Usuario> usuarios;
    private Usuario usuario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt1.setText("Bienvenido, !");
        ArrayList<String[]> opciones = verOpciones("opciones.txt");
        for(String[] s : opciones){
            for(int i = 1; i < s.length;i++){
                Button btn1 = new Button(s[i]);
                vbox.getChildren().add(btn1);
            }
            vbox.setSpacing(20);
        }
        
    }
    public ArrayList<String[]> verOpciones(String nomFile){
        ArrayList<String[]> opciones = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(nomFile))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                opciones.add(tokens);
            }
        } catch(IOException i){
            System.out.println(i.getMessage());
        }
        return opciones;
    }
    
    public void setInformacion(ArrayList<Vehiculo> vehiculos, ArrayList<Usuario> usuarios, Usuario usuario){
        this.vehiculos = vehiculos;
        this.usuarios = usuarios;
        this.usuario = usuario;
    }
    
}
