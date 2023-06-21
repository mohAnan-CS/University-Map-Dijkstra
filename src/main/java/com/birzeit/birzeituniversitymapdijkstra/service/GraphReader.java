package com.birzeit.birzeituniversitymapdijkstra.service;

import com.birzeit.birzeituniversitymapdijkstra.model.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphReader {

    public Graph readGraphFromFile(String path) {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            Boolean isNumBuildEdgeRead = false;
            int numberOfBuildings = 0;
            int numberOfEdges = 0;
            int counter = 0;
            while ((line = reader.readLine()) != null) {

                if (!isNumBuildEdgeRead){
                    //Read Number of buildings and edges
                    String[] lineSplit = line.split(",");
                    numberOfBuildings = Integer.parseInt(lineSplit[0].trim());
                    numberOfEdges = Integer.parseInt(lineSplit[1].trim());
                    isNumBuildEdgeRead = true;
                }else{

                    if (counter < numberOfBuildings){
                        //Read Buildings
                        counter++;
                    }else{
                        //Read Edges
                    }

                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  new Graph();

    }

}
