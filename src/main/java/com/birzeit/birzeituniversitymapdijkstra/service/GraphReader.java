package com.birzeit.birzeituniversitymapdijkstra.service;

import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GraphReader {

    public List<Vertex> readGraphFromFile(String path) throws Exception {

        Graph graph = new Graph();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            Boolean isNumBuildEdgeRead = false;
            int numberOfBuildings = 0;
            int numberOfEdges = 0;
            int counter = 0;
            while ((line = reader.readLine()) != null) {

                if (!isNumBuildEdgeRead){
                    //Read Number of buildings.txt and edges
                    String[] lineSplit = line.split(",");
                    numberOfBuildings = Integer.parseInt(lineSplit[0].trim());
                    numberOfEdges = Integer.parseInt(lineSplit[1].trim());
                    System.out.println("Number of Buildings: " + numberOfBuildings);
                    System.out.println("Number of Edges: " + numberOfEdges);
                    isNumBuildEdgeRead = true;
                }else{

                    if (counter < numberOfBuildings){

                        //Read Buildings
                        String[] lineSplit = line.split(",");
                        String buildingName = lineSplit[0].trim();
                        double xCoordinate = Double.parseDouble(lineSplit[1].trim());
                        double yCoordinate = Double.parseDouble(lineSplit[2].trim());
                        graph.addVertex(buildingName.toLowerCase(), xCoordinate, yCoordinate);
                        System.out.println("Building Name: " + buildingName);
                        System.out.println("X Coordinate: " + xCoordinate);
                        System.out.println("Y Coordinate: " + yCoordinate);

                        System.out.println("====================================");
                        counter++;

                    }else{

                        //Read Edges
                        String[] lineSplit = line.split(",");
                        String sourceBuilding = lineSplit[0].trim();
                        String destinationBuilding = lineSplit[1].trim();
                        int distance = Integer.parseInt(lineSplit[2].trim());
                        graph.addAdjacent(sourceBuilding, destinationBuilding, distance);
                        System.out.println("Source Building: " + sourceBuilding);
                        System.out.println("Destination Building: " + destinationBuilding);
                        System.out.println("Distance: " + distance);

                    }
                }

            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (Exception exception){
            exception.printStackTrace();
        }

        System.out.println("REaderrrrrrrrrrrrrrrrrr");
        List<Vertex> vertices = Graph.buildingList;
        System.out.println(vertices.size());
        return Graph.buildingList;

    }

}
