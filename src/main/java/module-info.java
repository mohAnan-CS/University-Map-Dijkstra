module com.example.birzeituniversitymapdijkstra {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    opens com.birzeit.birzeituniversitymapdijkstra to javafx.fxml, javafx.graphics;
    exports com.birzeit.birzeituniversitymapdijkstra.controller;
}