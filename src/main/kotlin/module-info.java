module com.example.practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires org.json;
    requires kotlinx.coroutines.core;
    requires com.jfoenix;
    requires java.desktop;
    requires java.rmi;


    opens com.example.practice to javafx.fxml;
    exports com.example.practice;
}