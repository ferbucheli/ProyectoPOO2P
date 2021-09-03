/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.CasilleroException;
import ec.edu.espol.exceptions.ComboBException;
import ec.edu.espol.exceptions.CorreoException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.GFG;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author yober
 */
public class RegisterController implements Initializable {
    
    private ArrayList<Usuario> usuarios;
    private String rol;
    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField correo;
    @FXML
    private TextField organización;
    @FXML
    private PasswordField contraseña;
    @FXML
    private ComboBox comboB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Vendedor");
        roles.add("Comprador");
        roles.add("Ambos");
        comboB.setItems(FXCollections.observableArrayList(roles));
        usuarios = Usuario.cargarUsuarios("usuario.ser");
        System.out.println(usuarios);
    }

    @FXML
    private void registro(MouseEvent event) {
        
        String name = nombres.getText();
        String lastname = apellidos.getText();
        String mail;
        String org = organización.getText();
        String pass = contraseña.getText();

        
        int id = Util.nextIDUsuario(usuarios); // colocar el nombre del archivo
        try {
            if(Usuario.validarCorreo(correo.getText())){
                mail = correo.getText();
                if((name.equals(""))||(lastname.equals(""))||(mail.equals(""))||(org.equals(""))||(pass.equals(""))){
                    try {
                        throw new CasilleroException("Debe rellenar todos los casilleros");
                    } catch (CasilleroException ex) {
                        Alert a = new Alert(AlertType.ERROR,ex.getMessage());
                        a.show();
                    }
                }
                else if(this.rol==null){
                    throw new ComboBException("Debe seleccionar algún rol");
                }
                else if(Usuario.validarCorreo(usuarios, mail)){
                    FXMLLoader fxmlloader  = App.loadFXMLLoader("login");
                    App.setRoot(fxmlloader);
                    LoginController lc = fxmlloader.getController();
                    lc.setUsuario(id, mail, pass, name, lastname, org, this.rol);
                }
                else{
                        throw new CorreoException("El correo ya ha sido registrado");
                }
                
            }
        else{
                throw new CorreoException("El correo ingresado es incorrecto");
            }

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }catch (CorreoException ex) {
            Alert a = new Alert(AlertType.ERROR,ex.getMessage());
            a.show();
        
    }   catch (ComboBException ex) {
            Alert a = new Alert(AlertType.ERROR,ex.getMessage());
            a.show();
        }
    }


    @FXML
    private void tipo(ActionEvent event) {
        rol = (String)comboB.getValue();      
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader fxmlloader  = App.loadFXMLLoader("login");
            App.setRoot(fxmlloader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
