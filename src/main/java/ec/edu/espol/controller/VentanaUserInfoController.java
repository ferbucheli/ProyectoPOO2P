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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cb(MouseEvent event) {
    }

    @FXML
    private void cambiarRol(MouseEvent event) {
    }
    
}
