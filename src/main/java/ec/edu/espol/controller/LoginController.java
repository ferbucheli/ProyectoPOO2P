/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class LoginController implements Initializable {
    
    private ArrayList<Usuario> usuarios;

    @FXML
    private GridPane GridPaneTop;
    @FXML
    private GridPane GridPaneCenter;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button registrar;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        
    }    

    @FXML
    private void login(MouseEvent event) {
        
    }

    @FXML
    private void register(MouseEvent event) {
        try {
            FXMLLoader fxmlloader  = App.loadFXMLLoader("register");
            App.setRoot(fxmlloader);
            RegisterController rc = fxmlloader.getController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setUsuario(int id,String mail,String pass,String name,String lastname,String org,String rol){
        Usuario user = new Usuario(id,mail,pass,name,lastname,org,rol);
        usuarios.add(user);
    }
   
}
