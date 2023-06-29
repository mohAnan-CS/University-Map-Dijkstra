package com.birzeit.birzeituniversitymapdijkstra.controller;

import com.birzeit.birzeituniversitymapdijkstra.model.Building;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField textFiled;

    ArrayList<Building> arrayList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Distance = " + calculateDistance(619, 501,507, 584));

        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!textFiled.getText().trim().equalsIgnoreCase("")) {
                String buildingName = textFiled.getText();
                double x = event.getX();
                double y = event.getY();
                System.out.println("Building Name : " + buildingName + " at (" + x + ", " + y + ")");
                Building building = new Building(buildingName, x, y);
                arrayList.add(building);
                textFiled.setText("");
            }
        });



    }

    public List<Building> readBuildingDataFromFile(String filename) {
        List<Building> buildings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 3) {
                    String buildingName = data[0];
                    double x = Double.parseDouble(data[1]);
                    double y = Double.parseDouble(data[2]);
                    Building building = new Building(buildingName, x, y);
                    buildings.add(building);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return buildings;
    }


    public double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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
