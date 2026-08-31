module com.example.ds_interface {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ds_interface to javafx.fxml;
    exports com.example.ds_interface;
}