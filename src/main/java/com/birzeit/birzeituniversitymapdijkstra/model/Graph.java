package com.birzeit.birzeituniversitymapdijkstra.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    private HashMap<String, List<Vertex>> hashMapBuildings = new HashMap<>();

    public Graph() {

    }

    public void addVertex(String building) {
         if (!building.trim().equals("")){
            hashMapBuildings.put(building, new ArrayList<>());
         }
    }

    public void addAdjacent(String sourceBuilding, String destinationBuilding, int distance) {
        if (!sourceBuilding.trim().equals("") && !destinationBuilding.trim().equals("")) {
            if (distance > 0){
                hashMapBuildings.get(sourceBuilding).add(new Vertex(destinationBuilding, distance));
            }

        }
    }

    public void printGraph(){

        for (String key : hashMapBuildings.keySet()) {
            System.out.println("Building: " + key);
            for (Vertex vertex : hashMapBuildings.get(key)) {
                System.out.println(" --> Adjacent: " + vertex.getBuilding() + " Distance: " + vertex.getDistance());
            }
        }

    }




}
