module ec.edu.espol.proyecto2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    opens ec.edu.espol.proyecto2p to javafx.fxml;
    exports ec.edu.espol.proyecto2p;
    
    
}
