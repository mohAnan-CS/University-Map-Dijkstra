package com.birzeit.birzeituniversitymapdijkstra.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    private HashMap<Vertex, List<Edge>> hashMapBuildings ;
    private HashMap<String, Vertex> hashMapBuildingsNames;

    public Graph() {
        hashMapBuildings = new HashMap<>();
        hashMapBuildingsNames = new HashMap<>();
    }

    public void addVertex(String buildingName, double xCoordinate, double yCoordinate) {
         if (!buildingName.trim().equals("")){
             Vertex vertex = new Vertex(buildingName, xCoordinate, yCoordinate);
             hashMapBuildings.put(vertex, new ArrayList<>());
             hashMapBuildingsNames.put(buildingName.toLowerCase(), vertex);
         }
    }

    public void addAdjacent(String sourceBuilding, String destinationBuilding, int distance) {

        Vertex srcVertex = new Vertex(sourceBuilding.toLowerCase(), 0, 0);
        Vertex destVertex = new Vertex(destinationBuilding.toLowerCase(), 0, 0);
        boolean checkError = false;

        if (!sourceBuilding.trim().equals("") && !destinationBuilding.trim().equals("")) {
            if (distance >= 0){
                srcVertex = getVertex(sourceBuilding);
                destVertex = getVertex(destinationBuilding);
                if (hashMapBuildings.get(srcVertex) != null && hashMapBuildings.get(destVertex) != null){
                    hashMapBuildings.get(srcVertex).add(new Edge(sourceBuilding, destinationBuilding, distance));
                    checkError = true;
                }
            }
        }

        if (!checkError){
            checkError(srcVertex, destVertex, distance);
        }

    }

    private Vertex getVertex(String building) {

        //loop on hashmap and check if the building is found or not and return it
        for (Vertex vertex : hashMapBuildings.keySet()) {
            if (vertex.getBuilding().equalsIgnoreCase(building)) {
                return vertex;
            }
        }
        return null;
    }

    private void checkError(Vertex srcVertex, Vertex destVertex, int distance) {

        System.out.println(srcVertex    + " " + destVertex + " " + distance);

        if (distance == 0){
            throw new IllegalArgumentException("Distance can't be zero between '" + srcVertex.getBuilding() + "' and '" + destVertex.getBuilding() + "'");
        }

        if (distance < 0){
            throw new IllegalArgumentException("Distance can't be negative between '" + srcVertex.getBuilding() + "' and '" + destVertex.getBuilding() + "'");
        }

        if (srcVertex.getBuilding().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Source building can't be empty");
        }

        if (destVertex.getBuilding().trim().equalsIgnoreCase("")){
            throw new IllegalArgumentException("Destination building can't be empty");
        }

        if (hashMapBuildings.get(srcVertex) == null){
            throw new IllegalArgumentException("'" + srcVertex.getBuilding() + "' Source building doesn't exist");
        }

        if (hashMapBuildings.get(destVertex) == null){
            throw new IllegalArgumentException("'" + destVertex.getBuilding() + "' Destination building doesn't exist");
        }

    }

    public void printGraph(){

        System.out.println("printGraph() method");

        for (Vertex vertex : hashMapBuildings.keySet()) {
            System.out.print("Vertex : " + vertex );
            for (Edge edge : hashMapBuildings.get(vertex)) {
                System.out.print(" --> Edge { Building name : " + edge.getDestinationBuilding()
                        + ", Distance : " + edge.getDistance() + " }");
            }
            System.out.println();
        }

    }

}
