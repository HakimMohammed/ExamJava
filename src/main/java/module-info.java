module org.example.examjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examjava to javafx.fxml;
    exports org.example.examjava;
}