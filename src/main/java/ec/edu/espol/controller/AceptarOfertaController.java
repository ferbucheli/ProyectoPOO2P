/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jara-Cadena
 */
public class AceptarOfertaController implements Initializable {

 
    @FXML
    private TableView<Oferta> Tview;
    @FXML
    private TableColumn<Oferta, String> placa;
    
    private Usuario usuario;
    private ArrayList<Vehiculo> Vehiculos;
    private ArrayList<Usuario> Usuarios;
    @FXML
    private Button BTaceptar;
    
    @FXML
    private TableColumn<Oferta, Double> precioOfertado;
    @FXML
    private TableColumn<Oferta, String> correoComprador;

    /**
     * Initializes the controller class.
     */
    
        
     
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        Vehiculos= new ArrayList<>();
        
            this.Usuarios=Usuario.cargarUsuarios("usuarios.ser");
            for(Usuario U:this.Usuarios){
            
                if(U.getVehiculos().size()>0){
 
                    ArrayList<Vehiculo> Vehiculoslista=U.getVehiculos();
                    for(Vehiculo V:Vehiculoslista){
                        Vehiculos.add(V);            
                    }
                }
                else{
                
                }
           
        }
        
        
    }    
    
    
    
    public void setInformacion(Usuario usuario){
        this.usuario = usuario;
    }
    
    
    

    @FXML
    private void AceptarOferta(MouseEvent event) {
         Util.sendMail(this.usuario.getCorreo());
         Oferta selectedItem = Tview.getSelectionModel().getSelectedItem();
         Tview.getItems().remove(selectedItem);
         
        //Util.sendMail("migel_sjc2002@hotmail.com");
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
    private void MostarOfertas(MouseEvent event) {
        
        //ArrayList<Vehiculo> Ofertas=new ArrayList<>();
        
        
        
        ArrayList<Oferta> Ofertas =this.usuario.getOfertas();
        
        
        //Vehiculo.sort(Oferta::compareTo);
        
        ObservableList<Oferta> lista=FXCollections.observableArrayList(Ofertas);
        
        // TODO
        
        placa.setCellValueFactory(new PropertyValueFactory<Oferta, String>("placa"));
        
        precioOfertado.setCellValueFactory(new PropertyValueFactory<Oferta, Double>("precio_ofertado"));
        correoComprador.setCellValueFactory(new PropertyValueFactory<Oferta, String>("correo_comprador"));
        
        
        Tview.setItems(lista);
        
        
    }
    
    
   
}
