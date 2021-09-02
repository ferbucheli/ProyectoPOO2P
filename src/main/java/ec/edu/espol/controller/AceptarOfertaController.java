/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Vehiculo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Jara-Cadena
 */
public class AceptarOfertaController implements Initializable {


    @FXML
    private HBox Hbox;
    @FXML
    private TableView<Vehiculo> TablaCompleta;
    @FXML
    private TableColumn<Vehiculo, String> Tipo;
    @FXML
    private TableColumn<Vehiculo, String> Marca;
    @FXML
    private TableColumn<Vehiculo, Integer> Anio;
    @FXML
    private TableColumn<Vehiculo, Double> Precio;
    @FXML
    private TableColumn<Vehiculo, Double> Motor;
    @FXML
    private TableColumn<Vehiculo, String> Placa;
    @FXML
    private TableColumn<Vehiculo, String> Combustible;
    private ArrayList<Vehiculo> Vehiculos;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField filterField;
    
    ObservableList<Vehiculo> dataList = FXCollections.observableArrayList();
    //Vehiculo T1=new Vehiculo("auto","ptt582","ford","focus",1.6,2008,90000,"azul","gasolina",4,"automatico",15000);
    @FXML
    private Button botonAceptar;
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //this.Vehiculos=cargarVehiculo("proyectos.ser");
        dataList.addAll(this.Vehiculos);
        
                               
        
        Tipo.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("TipoVehiculo"));       
        
        Marca.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("Marca"));        
        Anio.setCellValueFactory(new PropertyValueFactory<Vehiculo,Integer>("Año"));   
        
        Precio.setCellValueFactory(new PropertyValueFactory<Vehiculo,Double>("Precio"));        
        Motor.setCellValueFactory(new PropertyValueFactory<Vehiculo,Double>("Tipomotor"));   
        Placa.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("Placa"));        
        Combustible.setCellValueFactory(new PropertyValueFactory<Vehiculo,String>("Tipocombustible"));   
        
        
        
        
         FilteredList<Vehiculo> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(employee.getAnio()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} 
                                
				else if (String.valueOf(employee.getPrecio()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Vehiculo> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TablaCompleta.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TablaCompleta.setItems(sortedData);
               
        
        
        
        
        // TODO
    }    

    @FXML
    private void Aceptar(MouseEvent event) {
        
        TablaCompleta.getItems().removeAll(TablaCompleta.getSelectionModel().getSelectedItem());
    }
    
}
