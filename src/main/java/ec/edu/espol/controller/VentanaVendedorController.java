    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyecto2p.App;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
    private Usuario usuario;
    @FXML
    private Button btnUserInfo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
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
    
    public void setInformacion(Usuario usuario){
        this.usuario = usuario;
        txt1.setText("Bienvenido, " + this.usuario.getNombres() + " " + this.usuario.getApellidos());
        ArrayList<String[]> opciones = verOpciones("opciones.txt");
        ponerBotones(opciones);
    }
    
    public void ponerBotones(ArrayList<String[]> opciones){
        if(usuario.getRol().equals("vendedor")){
            for(int i = 1; i < opciones.get(0).length; i++){
                Button btn1 = new Button(opciones.get(0)[i]);
                vbox.getChildren().add(btn1);
                evaluarBotones();
            }
            vbox.setSpacing(20);
        } else if(usuario.getRol().equals("comprador")){
            for(int i = 1; i < opciones.get(1).length; i++){
                Button btn1 = new Button(opciones.get(1)[i]);
                vbox.getChildren().add(btn1);
                evaluarBotones();
            }
            vbox.setSpacing(20);
        } else if(usuario.getRol().equals("ambos")){
            for(int i = 1; i < opciones.get(2).length; i++){
                Button btn1 = new Button(opciones.get(2)[i]);
                vbox.getChildren().add(btn1);
                evaluarBotones();
            }
            vbox.setSpacing(20);
        }
    }
    
    public void evaluarBotones(){
        for(Node node : vbox.getChildren()){
            Button btn = (Button)node;
            if(btn.getText().equals("Ingresar Nuevo Vehiculo")){
                btn.setOnMouseClicked((MouseEvent ev) ->{
                    try{
                        FXMLLoader fxmlloader = App.loadFXMLLoader("ventanaRegistroVehiculo");
                        App.setRoot(fxmlloader);
                        VentanaRegistroVehiculoController vrc = fxmlloader.getController();
                        vrc.setInformacion(usuario);
                    } catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                });
            } else if(btn.getText().equals("Ver Ofertas")){
                btn.setOnMouseClicked((MouseEvent ev) ->{
                    try{
                        FXMLLoader fxmlloader = App.loadFXMLLoader("AceptarOferta");
                        App.setRoot(fxmlloader);
                        //VentanaOfertasController voc = fxmlloader.getController();
                        //voc.setInformacion(usuarios, usuario);
                    } catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                });
            }  else if(btn.getText().equals("Ofertar por un Vehiculo")){
                btn.setOnMouseClicked((MouseEvent ev) ->{
                    try{
                        FXMLLoader fxmlloader = App.loadFXMLLoader("PresentacionV");
                        App.setRoot(fxmlloader);
                        PresentacionVController pvc = fxmlloader.getController();
                        pvc.setInformacion(usuario);
                    } catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                });
            }
        }
    }

    @FXML
    private void mostrarInformacionUsuario(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("ventanaUserInfo");
            App.setRoot(fxmlloader);
            VentanaUserInfoController vui = fxmlloader.getController();
            vui.setInformacion(usuario);
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
