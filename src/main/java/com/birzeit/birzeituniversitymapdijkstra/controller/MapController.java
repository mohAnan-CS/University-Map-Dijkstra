package com.birzeit.birzeituniversitymapdijkstra.controller;

import com.birzeit.birzeituniversitymapdijkstra.model.Building;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

        List<Building> buildingArrayList = readBuildingDataFromFile("buildings.txt");
        System.out.println(buildingArrayList.size());
        for (Building building : buildingArrayList) {
            System.out.println(building);
        }

        drawCircleOnMap(buildingArrayList);

    }

    private void drawCircleOnMap(List<Building> buildingsList) {

        for (int i = 0; i < buildingsList.size(); i++) {

            String buildingName = buildingsList.get(i).getBuildingName();
            double x = buildingsList.get(i).getxCoordinate();
            double y = buildingsList.get(i).getyCoordinate();

            Circle circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(6.5);
            anchorPane.getChildren().add(circle);

            Label label = new Label();
            label.setText(" " + buildingName + " ");

            circle.setOnMousePressed(mouseEvent -> {


            });

            circle.setOnMouseEntered(mouseEvent -> {

                label.setLayoutY(y - 14);
                label.setLayoutX(x);
                label.setStyle("-fx-font-size: 30");
                label.setStyle("-fx-background-color: #16ad35;");
                label.setStyle("-fx-font-weight: bold");
                BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), null);

                // Create a background with the background fill
                Background background = new Background(backgroundFill);

                // Set the background of the label
                label.setBackground(background);

                anchorPane.getChildren().add(label);

            });

            circle.setOnMouseExited(mouseEvent -> {

                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);


            });


        }

    }

    private void collectBuildingData(){

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
