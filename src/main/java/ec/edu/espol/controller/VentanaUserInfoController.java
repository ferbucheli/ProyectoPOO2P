/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private HBox rolPane;
    @FXML
    private GridPane gridPaneInfo;
    
    private ArrayList<Usuario> usuarios;
    private Usuario usuario;

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
    }
    
    public void setInformacion(ArrayList<Usuario> usuarios, Usuario usuario){
        this.usuarios = usuarios;
        this.usuario = usuario;
        ArrayList<String> userInfo = usuario.informacionUsuario();
        for(int i = 0; i < gridPaneInfo.getColumnCount(); i++){
            Text txt = new Text(userInfo.get(i));
            GridPane.setConstraints(txt, i, 1);
            gridPaneInfo.getChildren().add(txt);
        }
    }
    
}
