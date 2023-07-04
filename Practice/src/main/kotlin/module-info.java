module com.example.practice {
    requires javafx.controls;
    requires javafx.fxml;
                requires kotlin.stdlib;
    
                            
    opens com.example.practice to javafx.fxml;
    exports com.example.practice;
}