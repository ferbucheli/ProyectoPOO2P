/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.CasilleroException;
import ec.edu.espol.exceptions.LoginException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
        
        usuarios = Usuario.cargarUsuarios("usuarios.ser");
        System.out.println(usuarios);
    }    

    @FXML
    private void login(MouseEvent event) {
        
        try {
            if((password.getText().equals(""))||(user.getText().equals(""))){
                throw new CasilleroException("Debe llenar todos los campos");
            }
            else{
                for(Usuario p:usuarios){
                if((p.getCorreo().equals(user.getText()))&&(p.getClave().equals(password.getText()))){
                    FXMLLoader fxmlloader  = App.loadFXMLLoader("ventanaVendedor");
                    App.setRoot(fxmlloader);
                    VentanaVendedorController ventanaV = fxmlloader.getController();
                    ventanaV.setInformacion(p);
                }
                else{
                    try {
                        throw new LoginException("EL usuario y la contraseña son incorrectos");
                    } catch (LoginException ex) {
                        Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                        a.show();
                        }
                    }
                }
            }    
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (CasilleroException ex) {
            Alert a = new Alert(AlertType.ERROR,ex.getMessage());
            a.show();
        }
        
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
        Usuario usuario = new Usuario(id,mail,pass,name,lastname,org,rol);
        usuarios.add(usuario);
        Usuario.guardarUsuarios("usuarios.ser", usuarios);
    }
    
}
