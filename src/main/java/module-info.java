module ec.edu.espol.proyecto2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    
    
    opens ec.edu.espol.proyecto2p to javafx.fxml;
    opens ec.edu.espol.controller to javafx.fxml;
    opens ec.edu.espol.model to javafx.fxml;
    
    exports ec.edu.espol.proyecto2p;
    exports ec.edu.espol.controller;
    exports ec.edu.espol.model;
    requires java.mail;
    
    
    
    
    
   
    
    
    
}
