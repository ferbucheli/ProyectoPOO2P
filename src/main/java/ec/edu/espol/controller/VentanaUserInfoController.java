/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.CasilleroException;
import ec.edu.espol.exceptions.ContrasenaException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class VentanaUserInfoController implements Initializable {

    @FXML
    private Button btnContrasena;
    @FXML
    private Button btnRol;
    @FXML
    private VBox contrasenaPane;
    @FXML
    private VBox rolPane;
    @FXML
    private GridPane gridPaneInfo;
    
    private ArrayList<Usuario> usuarios;
    private Usuario usuario;
    @FXML
    private Button btnRegresar;
    
    private static boolean contrasenaOpened = false;
    private static boolean rolOpened = false;
            
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void cb(MouseEvent event) {
    }

    @FXML
    private void cambiarRol(MouseEvent event) {
        if(!rolOpened){
            rolCasillero();
            rolOpened = true;
        } else{
            rolPane.getChildren().clear();
            rolOpened = false;
        }
    }
    
    public void setInformacion(ArrayList<Usuario> usuarios, Usuario usuario){
        this.usuarios = usuarios;
        this.usuario = usuario;
        ponerDatos();
    }
    
    public void ponerDatos(){
        ArrayList<String> userInfo = usuario.informacionUsuario();
        for(int i = 0; i < gridPaneInfo.getRowCount(); i++){
            Label lbl = new Label(userInfo.get(i));
            GridPane.setConstraints(lbl, 1, i);
            gridPaneInfo.getChildren().add(lbl);
        }
    }

    @FXML
    private void cambiarContrasena(MouseEvent event) {
        if(!contrasenaOpened){
            contrasenaCasillero();
            contrasenaOpened = true;
        } else{
            contrasenaPane.getChildren().clear();
            contrasenaOpened = false;
        }
    }
    
    public void limpiar(){
        ObservableList list = gridPaneInfo.getChildren();
        for(int i = 5; i < list.size(); i++){
            Node node = (Node)list.get(i);
            Label lbl = (Label)node;
            lbl.setText("");
        }
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("ventanaVendedor");
            App.setRoot(fxmlloader);
            VentanaVendedorController vvc = fxmlloader.getController();
            vvc.setInformacion(usuarios, usuario);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void contrasenaCasillero(){
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        Text txt1 = new Text("Nueva contraseña:");
        TextField txtf1 = new TextField();
        Text txt2 = new Text("Confirmar nueva nontraseña:");
        TextField txtf2 = new TextField();
        hbox1.getChildren().addAll(txt1,txtf1);
        hbox2.getChildren().addAll(txt2,txtf2);
        hbox1.setSpacing(8);
        hbox2.setSpacing(8);
        Button btn = new Button("Cambiar");
        btn.setOnMouseClicked((MouseEvent eve) ->{
            try {
                String contrasena = txtf1.getText();
                String contrasenaC = txtf2.getText();
                if(contrasena.equals("") && contrasenaC.equals(""))
                    throw new CasilleroException("Debe de llenar los 2 casilleros!");
                else if(!contrasena.equals(contrasenaC))
                    throw new ContrasenaException("Las contraseñas deben coincidir!");
                usuario.setClave(contrasena);
                contrasenaPane.getChildren().clear();
                contrasenaOpened = false;
            } catch (CasilleroException ex) {
                Alert a = new Alert(AlertType.ERROR, ex.getMessage());
                a.show();
            } catch (ContrasenaException e) {
                Alert a = new Alert(AlertType.ERROR, e.getMessage());
                a.show();
            }
        });
        contrasenaPane.getChildren().addAll(hbox1,hbox2,btn);
    }
    
    public void rolCasillero(){
        RadioButton r1 = new RadioButton("Vendedor");
        RadioButton r2 = new RadioButton("Comprador");
        RadioButton r3 = new RadioButton("Ambos");
        ToggleGroup tg = new ToggleGroup();
        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        r3.setToggleGroup(tg);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(r1,r2,r3);
        hbox.setSpacing(5);
        rolPane.getChildren().add(hbox);
        Button btn = new Button("Cambiar");
        btn.setOnMouseClicked((MouseEvent eve)-> {
            //Usuario.extraerUsuario(usuarios, usuario.getCorreo());
            if(r1.isSelected())
                usuario.setRol(r1.getText().toLowerCase());
            else if(r2.isSelected())
                usuario.setRol(r2.getText().toLowerCase());
            else if(r3.isSelected())
                usuario.setRol(r3.getText().toLowerCase());
            limpiar();
            ponerDatos();
            rolPane.getChildren().clear();
            rolOpened = false;
        });
        rolPane.getChildren().add(btn);
        rolPane.setSpacing(5);
    }
}
