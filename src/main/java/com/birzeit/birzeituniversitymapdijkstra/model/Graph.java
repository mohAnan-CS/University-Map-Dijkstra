package com.birzeit.birzeituniversitymapdijkstra.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {


    List<Vertex> buildingList;
    private int counter = 0;

    public Graph() {
        buildingList = new ArrayList<>();
    }

    public void addVertex(String buildingName, double xCoordinate, double yCoordinate) {
         if (!buildingName.trim().equals("")){
             Vertex vertex = new Vertex(buildingName, xCoordinate, yCoordinate);
             vertex.setEdgesList(new ArrayList<>());
             vertex.setIndex(counter);
             counter++;
             buildingList.add(vertex);
         }else{
             throw new IllegalArgumentException("Building name can't be empty");
         }
    }

    public void addAdjacent(String sourceBuilding, String destinationBuilding, double distance) {

        Vertex srcVertex = getVertex(sourceBuilding);
        Vertex destVertex = getVertex(destinationBuilding);

        checkBuildingIsFound(srcVertex, destVertex, sourceBuilding, destinationBuilding);
        checkDistance(distance);

        int indexSrc = srcVertex.getIndex();
        destVertex.setDistance(distance);
        buildingList.get(indexSrc).getEdgesList().add(destVertex);

    }

    private void checkDistance(double distance) {

        if (distance == 0){
            throw new IllegalArgumentException("Distance can't be zero");
        }

        if (distance < 0){
            throw new IllegalArgumentException("Distance can't be negative");
        }

    }

    private void checkBuildingIsFound(Vertex srcVertex, Vertex destVertex, String sourceBuilding, String destinationBuilding) {

        if (srcVertex == null){
            throw new IllegalArgumentException("Source building '" + sourceBuilding + "' doesn't exist");
        }

        if (destVertex == null){
            throw new IllegalArgumentException("Destination building '" + destinationBuilding + "' doesn't exist");
        }

    }

    private Vertex getVertex(String building) {

        //loop on buildingList and return the vertex
        for (Vertex vertex : buildingList) {
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

    }

    public void printGraph(){

        for (Vertex vertex : buildingList) {
            System.out.print(vertex.toString());
            for (Vertex edge : vertex.getEdgesList()) {
                System.out.print(" --> " + edge.toString());
            }
            System.out.println();
        }

    }

}
