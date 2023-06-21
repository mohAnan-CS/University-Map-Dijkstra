module com.example.birzeituniversitymapdijkstra {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.birzeit.birzeituniversitymapdijkstra to javafx.fxml;
    exports com.birzeit.birzeituniversitymapdijkstra;
}