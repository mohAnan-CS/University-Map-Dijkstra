package com.birzeit.birzeituniversitymapdijkstra.controller;

import com.birzeit.birzeituniversitymapdijkstra.model.Building;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFiled;

    ArrayList<Building> arrayList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String buildingName = textFiled.getText();
            double x = event.getX();
            double y = event.getY();
            System.out.println("Building Name : " +  buildingName +  " at (" + x + ", " + y + ")");
            Building building = new Building(buildingName, x, y);
            arrayList.add(building);
        });



    }

    @FXML
    void buttonOnAction(ActionEvent event) {

        writeBuildingToFile("C:\\Users\\twitter\\IdeaProjects\\BirzeitUniversityMapDijkstra\\buildings.txt");

    }

    private void writeBuildingToFile(String path){

        try (FileWriter writer = new FileWriter(path)) {
            for (Building building : arrayList) {
                String buildingInfo = building.getBuildingName() + ", " + building.getxCoordinate() + ", " + building.getyCoordinate() + "\n";
                writer.write(buildingInfo);
            }
            System.out.println("Building information has been written to the file path: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
