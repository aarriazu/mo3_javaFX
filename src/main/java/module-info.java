module com.example.m03_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.m03_javafx to javafx.fxml;
    exports com.example.m03_javafx;
}