/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Vehiculo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jara-Cadena
 */
public class AceptarOfertaController implements Initializable {

    @FXML
    private TableView<Vehiculo> Tview;
    @FXML
    private TableColumn<Vehiculo, String> placa;
    @FXML
    private TableColumn<Vehiculo, String> marca;

    /**
     * Initializes the controller class.
     */
     Vehiculo T1=new Vehiculo(15,"auto",15, "gdf2", "lotus", "1600", 2002, "leis", 15.2, "rojo","gasolina", 15.2, "4", "automatica","");
        
        
     
    ObservableList<Vehiculo> lista=FXCollections.observableArrayList(T1);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        placa.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("placa"));
        marca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
        Tview.setItems(lista);
    }    
    
}
