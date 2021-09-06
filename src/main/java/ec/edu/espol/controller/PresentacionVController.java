/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import static ec.edu.espol.model.Usuario.guardarUsuarios;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Jara-Cadena
 */
public class PresentacionVController implements Initializable {

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
    private ArrayList<Usuario> Usuarios;
    private ArrayList<Vehiculo> Vehiculos;
    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TextField filterField;
    
    
    //Vehiculo T1=new Vehiculo("auto","ptt582","ford","focus",1.6,2008,90000,"azul","gasolina",4,"automatico",15000);
    @FXML
    private HBox Hbox;
    @FXML
    private HBox hb;
    @FXML
    private TextField PrecioOF;
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Vehiculos= new ArrayList<>();
        
        this.Usuarios=Usuario.cargarUsuarios("usuarios.ser");
        for(Usuario U:this.Usuarios){
            
                if(U.getVehiculos().size()==0){
                }
                else{
                    ArrayList<Vehiculo> Vehiculoslista=U.getVehiculos();
                    for(Vehiculo V:Vehiculoslista){
                        Vehiculos.add(V);            
                    }
                }
            
           
        }
        
        ObservableList<Vehiculo> lista=FXCollections.observableArrayList(Vehiculos);
        
        
        
        Tipo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("tipo"));
        Marca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
        Anio.setCellValueFactory(new PropertyValueFactory<Vehiculo, Integer>("anio"));
        Precio.setCellValueFactory(new PropertyValueFactory<Vehiculo, Double>("precio"));
        Motor.setCellValueFactory(new PropertyValueFactory<Vehiculo, Double>("motor"));
        Placa.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("placa"));
        Combustible.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("combustible"));
        
        FilteredList<Vehiculo> filteredData = new FilteredList<>(lista, b -> true);
		
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
        
        
    }    
    
    public void setInformacion(Usuario usuario){
        this.usuario = usuario;
    }
    
    

    @FXML
    private void Volver(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("ventanaVendedor");
            App.setRoot(fxmlloader);
            VentanaVendedorController vvc = fxmlloader.getController();
            vvc.setInformacion(usuario);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Ofertar(MouseEvent event) {
        //Oferta(int id, int id_Comprador, int id_Vehiculo, double precio_ofertado, String correo_comprador)
        
        
        if(PrecioOF.getText().isEmpty()){
            Alert A1= new Alert(Alert.AlertType.ERROR,"Escriba el monto a ofertar");
            A1.show();
            
        
        }
        else{
            
            int IDusuario=this.usuario.getId();
            String Correo=this.usuario.getCorreo();
            int ID=TablaCompleta.getSelectionModel().getSelectedItem().getId();
            double precioOfertado=Double.parseDouble(PrecioOF.getText());
            
            
            Oferta Ofert=new Oferta(IDusuario,IDusuario,ID,precioOfertado,Correo);
            
            Vehiculo V =TablaCompleta.getSelectionModel().getSelectedItem();
            int idus=V.getId_usuario();
            Usuario dueno=Usuario.extraerUsuario(idus, this.Usuarios);
            dueno.getOfertas().add(Ofert);
            this.usuario.getOfertas().add(Ofert);
   
            //guardarUsuarios("usuarios.ser",this.Usuarios);
            Util.actualizar(this.Usuarios,"usuarios.ser");
            
            
             try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("ventanaVendedor");
            App.setRoot(fxmlloader);
            VentanaVendedorController vvc = fxmlloader.getController();
            vvc.setInformacion(usuario);
            } catch (IOException ex) {
            ex.printStackTrace();
            }
            
            
            
            
        }
        
    }
    
}
