/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
    }
    
}