package com.birzeit.birzeituniversitymapdijkstra.controller;

import com.birzeit.birzeituniversitymapdijkstra.model.DijkstraResult;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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

    @FXML
    private Button buildingNameBtn;

    @FXML
    private Button resetBtn;

    private int flag = 0; // flag == 0 means the user didn't choose a source building yet , flag == 1 means the user chose a source building

    private Circle srcCircle = null;
    private Circle destCircle = null;
    private Label srcLabel = null;
    private Label destLabel = null;


    public static List<Vertex> buildingList = new ArrayList<>();
    public List<Line> lineList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            buildingNameBtn.setVisible(false);
            textFiled.setVisible(false);

            //Read data from building file
            GraphReader graphReader = new GraphReader();
            buildingList = graphReader.readGraphFromFile("buildings.txt");

            drawCircleOnMap(buildingList);
            fillComboBox(buildingList);
            customizeComboBox(sourceComboBox);
            customizeComboBox(destinationComboBox);
            customizePathArea();
            //distanceTextField.setDisable(true);

        } catch (Exception e) {
            showAlert("Error", "Error", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private void customizePathArea(){

        pathTextArea.setWrapText(true); // Enable text wrapping
        pathTextArea.setStyle("-fx-font-size: 14px; -fx-font-family: Arial;-fx-display-caret:true;");
        //pathTextArea.setDisable(true);

    }

    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void travelOnAction() {

        try {


            if (sourceComboBox.getValue().trim().equalsIgnoreCase("") || destinationComboBox.getValue().trim().equalsIgnoreCase("")) {
                throw new IllegalArgumentException("Please choose a source and destination building");
            }

            anchorPane.getChildren().removeAll(lineList);

            String sourceBuilding = sourceComboBox.getValue();
            String destBuilding = destinationComboBox.getValue();

            Dijkstra dijkstra = new Dijkstra();
            DijkstraResult dijkstraResult = dijkstra.findShortestPath(buildingList, sourceBuilding, destBuilding);

            List<String> path = dijkstraResult.getPath();
            double distance = dijkstraResult.getDistance();

            distanceTextField.setText(String.valueOf(distance));

            StringBuilder pathString = new StringBuilder();
            int count = 0;
            for (String s : path) {
                if (count != 0) {
                    if (path.size() - 1 == count) {
                        pathString.append(" \n ");
                        pathString.append(s);
                        pathString.append(" \n ");
                        break;
                    }
                    pathString.append(" \n ");
                }
                pathString.append(s);
                pathString.append(" \n ");
                pathString.append(" \u2193 ");
                pathString.append(" \n ");
                count++;
            }

            pathTextArea.setText(pathString.toString());
            drawLinesPath(path);

        }catch (IllegalArgumentException e) {
            showAlert("Error", "Error", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception exception) {
            showAlert("Error", "Error", exception.getMessage(), Alert.AlertType.ERROR);
        }

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

            Circle circle = createCircle(x,y);
            anchorPane.getChildren().add(circle);

            Label label = new Label();
            label.setText(" " + buildingName + " ");

            AtomicBoolean isChosen = new AtomicBoolean(false);

            setCircleOnMousePressed(buildingName, x, y, circle, label, isChosen);
            setCircleOnMouseEntered(buildingName, x, y, circle, label, isChosen);
            setCircleOnMouseExited(buildingName, x, y, circle, label, isChosen);

        }

    }

    private Circle createCircle(double x, double y) {

        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(6.5);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);

        return circle;

    }

    private void setCircleOnMouseExited(String buildingName, double x, double y, Circle circle, Label label, AtomicBoolean isChosen) {

        circle.setOnMouseExited(mouseEvent -> {

            if (isChosen.get()) {

            }else {
                circle.setRadius(6.5);
                circle.setStrokeWidth(1.5);
                anchorPane.getChildren().remove(anchorPane.getChildren().size() - 1);
            }

        });

    }

    private void setCircleOnMouseEntered(String buildingName, double x, double y, Circle circle, Label label, AtomicBoolean isChosen) {

        circle.setOnMouseEntered(mouseEvent -> {

            if (!isChosen.get()) {

                label.setLayoutY(y - 27);
                label.setLayoutX(x);
                label.setStyle("-fx-font-weight: bold;");
                label.setStyle("-fx-text-fill: white;");
                label.setFont(Font.font(15));
                circle.setRadius(8.5);
                circle.setStrokeWidth(2.5);

                BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, new CornerRadii(10), null);
                Background background = new Background(backgroundFill);

                label.setBackground(background);
                anchorPane.getChildren().add(label);

            }

        });

    }

    private void setCircleOnMousePressed(String buildingName, double x, double y, Circle circle, Label label, AtomicBoolean isChosen) {

        circle.setOnMousePressed(mouseEvent -> {

            if (srcCircle == null || destCircle == null){

                if (isChosen.get()){
                    System.out.println("isChosen : " + isChosen.get() + " " + buildingName);
                    isChosen.set(false);
                    circle.setFill(Color.RED);
                }else{
                    isChosen.set(true);
                    if (flag == 0){
                        sourceComboBox.setValue(buildingName);
                        circle.setFill(Color.BLACK);
                        srcCircle = circle;
                        srcLabel = label;
                        flag = 1;
                    }else{
                        destinationComboBox.setValue(buildingName);
                        circle.setFill(Color.YELLOW);
                        flag = 0;
                        destCircle = circle;
                        destLabel = label;
                    }
                }

                circle.setStrokeWidth(3.5);

            }

        });

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

        sourceComboBox.setItems(listSource);
        destinationComboBox.setItems(listDest);

    }

    private void customizeComboBox(ComboBox<String> comboBox){

        comboBox.setStyle("-fx-background-color: #e33131;" +
                "-fx-font-size: 14px;" +
                " -fx-padding: 5px; " +
                "-fx-font-weight: bold ;" +
                "-fx-border-color: #1e1c1c; " +
                "-fx-border-width: 4px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 16px;" +
                "-fx-cell-hover-color: #e33131; ");

        comboBox.setCellFactory(param -> new ListCell<String>() {
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

    @FXML
    void resetBtnOnAction(ActionEvent event) {

        destinationComboBox.setValue("");
        sourceComboBox.setValue("");
        pathTextArea.setText("");
        distanceTextField.setText("");
        anchorPane.getChildren().removeIf(node ->
                node instanceof Circle || node instanceof Line || node instanceof Label);
        buildingList = null;
        flag = 0;
        srcCircle = null;
        destCircle = null;

        GraphReader graphReader = new GraphReader();
        try {
            buildingList = graphReader.readGraphFromFile("buildings.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        drawCircleOnMap(buildingList);
        fillComboBox(buildingList);


    }


}
