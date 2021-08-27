/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class VentanaRegistroVehiculoController implements Initializable{

    @FXML
    private RadioButton rButton1;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton rButton3;
    @FXML
    private RadioButton rButton2;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnAdjuntar;
    
    private ArrayList<String[]> caracteristicas;
    @FXML
    private AnchorPane aPane1;
    private Pane pane1;
    @FXML
    private ImageView imv1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.caracteristicas = leerCaracteristicas("caracteristicas.txt");
    }    

    @FXML
    private void registroTipo(ActionEvent event) {
        gridPane.getChildren().clear();
        if(rButton1.isSelected()){
            this.agregarCaracteristicas(this.caracteristicas.get(0));
        } else if(rButton2.isSelected()){
            this.agregarCaracteristicas(this.caracteristicas.get(1));
        } else if(rButton3.isSelected()){
            this.agregarCaracteristicas(this.caracteristicas.get(2));
        }
    }

    @FXML
    private void registrar(MouseEvent event) {
        File img = new File(imv1.getImage().getUrl());
        String ruta = img.getAbsolutePath().substring(img.getAbsolutePath().lastIndexOf("\\") + 1);
        String newPath = "img/";
        
        File directory = new File(newPath);
        if(!directory.exists())
            directory.mkdirs();
        
        File sourceFile = new File(img.getAbsolutePath());
        File destination = new File(newPath + ruta);
        
        try {
            Files.copy(sourceFile.toPath(), destination.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<String[]> leerCaracteristicas(String nomFile){
        ArrayList<String[]> caracteristicas = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(nomFile))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                caracteristicas.add(tokens);
            }
        } catch(IOException i){
            System.out.println(i.getMessage());
        }
        return caracteristicas;
    }
    
    public void agregarCaracteristicas(String[] caracteristicas){
        for(int i = 1; i < caracteristicas.length; i++){
            Text txt = new Text(caracteristicas[i]);
            TextField txtf = new TextField();
            GridPane.setConstraints(txt, 0, i-1);
            GridPane.setConstraints(txtf, 1, i-1);
            gridPane.getChildren().addAll(txt,txtf);
        }
    }

    @FXML
    private void buscarImagen(MouseEvent event) {
        
        FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) aPane1.getScene().getWindow();
        File file = filechooser.showOpenDialog(stage);
        Image img = new Image(file.toURI().toString());
        
        if(file != null){
            imv1.setImage(img);
        }
    }
    
    public void guardarImagen(ArrayList<File> imagenes, String nomFile){
        
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFile))) {
            out.writeObject(imagenes);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
