/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.exceptions.OfertaException;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.proyecto2p.App;
import ec.edu.espol.util.Util;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox cbox;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    
    
    public void setInformacion(Usuario usuario){
        Vehiculos= new ArrayList<>();
        this.Usuarios = Usuario.cargarUsuarios("usuarios.ser");
        for(Usuario u : this.Usuarios){
            if(u.getVehiculos().size()>0){
                ArrayList<Vehiculo> Vehiculoslista = u.getVehiculos();
                for(Vehiculo V:Vehiculoslista){
                    Vehiculos.add(V);            
                }
            }
        }
        this.usuario = usuario;
        ArrayList<String> placas = usuario.obtenerPlacas();
        cbox.setItems(FXCollections.observableArrayList(placas));
    }    
    
    
    

    @FXML
    private void AceptarOferta(MouseEvent event) {
        Oferta oferta = Tview.getSelectionModel().getSelectedItem();
        Util.sendMail(oferta.getCorreo_comprador());
        Vehiculo v = Vehiculo.extraerVehiculo(Usuarios, oferta.getPlaca());
        deleteFile(v);
        Usuarios = Vehiculo.borrarVehiculo(Usuarios, oferta.getPlaca());
        Usuarios = Oferta.borrarOfertas(Usuarios, oferta.getPlaca());
        Util.actualizar(Usuarios, "usuarios.ser");
        Usuario u = Usuario.extraerUsuario(this.usuario.getId(), Usuarios);
        Tview.getItems().clear();
        setInformacion(u);
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
        String item = (String)cbox.getValue();
        if(item == null){
            ArrayList<Oferta> ofertas =this.usuario.getOfertas();
            ofertas.sort(Oferta::compareTo);
            ObservableList<Oferta> lista=FXCollections.observableArrayList(ofertas);
            placa.setCellValueFactory(new PropertyValueFactory<Oferta, String>("placa"));
            precioOfertado.setCellValueFactory(new PropertyValueFactory<Oferta, Double>("precio_ofertado"));
            correoComprador.setCellValueFactory(new PropertyValueFactory<Oferta, String>("correo_comprador"));
            Tview.setItems(lista);
        } else{
            try {
                ArrayList<Oferta> ofertas = this.usuario.getOfertas(item);
                ofertas.sort(Oferta::compareTo);
                ObservableList<Oferta> lista=FXCollections.observableArrayList(ofertas);
                placa.setCellValueFactory(new PropertyValueFactory<Oferta, String>("placa"));
                precioOfertado.setCellValueFactory(new PropertyValueFactory<Oferta, Double>("precio_ofertado"));
                correoComprador.setCellValueFactory(new PropertyValueFactory<Oferta, String>("correo_comprador"));
                Tview.setItems(lista);
            } catch (OfertaException ex) {
                Alert a = new Alert(AlertType.WARNING, ex.getMessage());
                a.show();
            }
        }
    }
    
    public void deleteFile(Vehiculo v){
        try {
            Path imagesPath = Paths.get("img/" + v.getRutaImg());
            Files.delete(imagesPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
