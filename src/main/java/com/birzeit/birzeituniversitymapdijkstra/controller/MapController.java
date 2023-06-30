package com.birzeit.birzeituniversitymapdijkstra.controller;

import com.birzeit.birzeituniversitymapdijkstra.model.Building;
import com.birzeit.birzeituniversitymapdijkstra.model.DijkstraResult;
import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;
import com.birzeit.birzeituniversitymapdijkstra.service.Dijkstra;
import com.birzeit.birzeituniversitymapdijkstra.service.GraphReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

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

    @FXML
    private ComboBox<String> sourceComboBox;

    @FXML
    private ComboBox<String> destinationComboBox;

    @FXML
    private TextField distanceTextField;

    @FXML
    private TextArea pathTextArea;

    public static List<Vertex> buildingList = new ArrayList<>();
    public List<Line> lineList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            //Read data from building file
            GraphReader graphReader = new GraphReader();
            buildingList = graphReader.readGraphFromFile("buildings.txt");
            drawCircleOnMap(buildingList);
            fillComboBox(buildingList);

            pathTextArea.setWrapText(true); // Enable text wrapping
            pathTextArea.setStyle("-fx-font-size: 14px; -fx-font-family: Arial;-fx-display-caret:true;"); // Customize font size and family
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

    @FXML
    void travelOnAction() {

        anchorPane.getChildren().removeAll(lineList);

        String sourceBuilding = sourceComboBox.getValue();
        String destBuilding = destinationComboBox.getValue();

        Dijkstra dijkstra = new Dijkstra();
        DijkstraResult dijkstraResult = dijkstra.findShortestPath(buildingList,sourceBuilding,destBuilding);

        List<String> path = dijkstraResult.getPath();
        double distance = dijkstraResult.getDistance();

        distanceTextField.setText(String.valueOf(distance));

        StringBuilder pathString = new StringBuilder();
        int count = 0;
        for (String s : path) {
            if (count == 0){
                pathString.append(s);
                pathString.append(" \n ");
                pathString.append(" \u2193 ");
                pathString.append(" \n ");
                count++;
            }else {
                if (path.size()-1 == count){
                    pathString.append(" \n ");
                    pathString.append(s);
                    pathString.append(" \n ");
                    break;
                }
                pathString.append(" \n ");
                pathString.append(s);
                pathString.append(" \n ");
                pathString.append(" \u2193 ");
                pathString.append(" \n ");
                count++;
            }

        }

        pathTextArea.setText(pathString.toString());
        drawLinesPath(path);


    }

    private void drawLinesPath(List<String> path){

        //loop on path list
        for (int i = 0; i < path.size()-1; i++) {
            String buildingOne = path.get(i);
            String buildingTwo = path.get(i+1);
            Vertex vertexOne = getVertex(buildingOne.trim());
            Vertex vertexTwo = getVertex(buildingTwo.trim());

            double x1 = vertexOne.getXCoordinate();
            double y1 = vertexOne.getYCoordinate();
            double x2 = vertexTwo.getXCoordinate();
            double y2 = vertexTwo.getYCoordinate();

            createLine(x1,y1,x2,y2);
        }

    }

    private void createLine(double x1, double y1, double x2, double y2) {

        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStrokeWidth(3);
        line.setStroke(Color.RED);
        //Create array to store line nodes
        lineList.add(line);

        anchorPane.getChildren().add(line);

    }

    public Vertex getVertex(String building) {

        //loop on buildingList and return the vertex
        for (Vertex vertex : buildingList) {
            if (vertex.getBuilding().trim().equalsIgnoreCase(building)) {
                return vertex;
            }
        }
        return null;
    }

    private void drawCircleOnMap(List<Vertex> buildingsList) {

        for (int i = 0; i < buildingsList.size(); i++) {

            String buildingName = buildingsList.get(i).getBuilding();
            double x = buildingsList.get(i).getXCoordinate();
            double y = buildingsList.get(i).getYCoordinate();

            Circle circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(6.5);
            circle.setFill(Color.RED);
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(1.5);
            anchorPane.getChildren().add(circle);

            Label label = new Label();
            label.setText(" " + buildingName + " ");

            circle.setOnMousePressed(mouseEvent -> {

                System.out.println(buildingName);

            });

            circle.setOnMouseEntered(mouseEvent -> {

                label.setLayoutY(y - 27);
                label.setLayoutX(x);
                label.setStyle("-fx-font-weight: bold;");
                label.setStyle("-fx-text-fill: white;");
                label.setFont(Font.font(15));
                circle.setRadius(8.5);
                circle.setStrokeWidth(2.5);

                BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(10), null);

                // Create a background with the background fill
                Background background = new Background(backgroundFill);

                // Set the background of the label
                label.setBackground(background);

                anchorPane.getChildren().add(label);

            });

            circle.setOnMouseExited(mouseEvent -> {

                circle.setRadius(6.5);
                circle.setStrokeWidth(1.5);
                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);

            });


        }

    }

    private void collectBuildingData(){

//        anchorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            if (!textFiled.getText().trim().equalsIgnoreCase("")) {
//                String buildingName = textFiled.getText();
//                double x = event.getX();
//                double y = event.getY();
//                System.out.println("Building Name : " + buildingName + " at (" + x + ", " + y + ")");
//                Building building = new Building(buildingName, x, y);
//                arrayList.add(building);
//                textFiled.setText("");
//            }
//        });

    }


    public double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @FXML
    void buttonOnAction(ActionEvent event) {

        writeBuildingToFile("C:\\Users\\twitter\\IdeaProjects\\BirzeitUniversityMapDijkstra\\buildings.txt");

    }

    private void writeBuildingToFile(String path){

//        try (FileWriter writer = new FileWriter(path)) {
//            for (Building building : arrayList) {
//                String buildingInfo = building.getBuildingName() + ", " + building.getxCoordinate() + ", " + building.getyCoordinate() + "\n";
//                writer.write(buildingInfo);
//            }
//            System.out.println("Building information has been written to the file path: " + path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void fillComboBox(List<Vertex> arrayList) {

        ObservableList<String> listSource = FXCollections.observableArrayList();
        ObservableList<String> listDest = FXCollections.observableArrayList();

        for (Vertex building : arrayList) {
            String buildingName = building.getBuilding();
            listSource.add(buildingName);
            listDest.add(buildingName);
        }

        sourceComboBox.setStyle("-fx-background-color: #e33131; -fx-font-size: 14px; -fx-padding: 5px; -fx-font-weight: bold ;" +
                "-fx-border-color: #1e1c1c; -fx-border-width: 4px; -fx-border-radius: 5px; -fx-background-radius: 16px;" +
                "-fx-cell-hover-color: #e33131; " );

        sourceComboBox.setItems(listSource);

        sourceComboBox.setCellFactory(param -> new ListCell<String>() {
            private final ImageView icon = new ImageView(new Image("C:\\Users\\twitter\\IdeaProjects\\BirzeitUniversityMapDijkstra\\src\\main\\java\\com\\birzeit\\birzeituniversitymapdijkstra\\images\\building.png"));

            {
                // Set the desired size of the icon
                icon.setFitWidth(16);
                icon.setFitHeight(16);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setGraphic(icon);
                }
            }
        });
        destinationComboBox.setItems(listDest);
        destinationComboBox.setStyle("-fx-background-color: #e33131; -fx-font-size: 14px; -fx-padding: 5px; -fx-font-weight: bold ;" +
                "-fx-border-color: #1e1c1c; -fx-border-width: 4px; -fx-border-radius: 5px; -fx-background-radius: 16px;" +
                "-fx-cell-hover-color: #e33131; " );

        destinationComboBox.setCellFactory(param -> new ListCell<String>() {
            private final ImageView icon = new ImageView(new Image("C:\\Users\\twitter\\IdeaProjects\\BirzeitUniversityMapDijkstra\\src\\main\\java\\com\\birzeit\\birzeituniversitymapdijkstra\\images\\building.png"));

            {
                // Set the desired size of the icon
                icon.setFitWidth(16);
                icon.setFitHeight(16);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setGraphic(icon);
                }
            }
        });

    }
}
