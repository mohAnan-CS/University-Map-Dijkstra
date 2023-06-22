package com.birzeit.birzeituniversitymapdijkstra.service;

import com.birzeit.birzeituniversitymapdijkstra.model.Edge;
import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.NodePath;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public List<String> findShortestPath(List<Vertex> buildingList, String source, String destination) {

        System.out.println(buildingList.size());

        //loop on buildingList and print the buildings
        for (Vertex vertex : buildingList) {
            System.out.println(vertex.getBuilding());
            for (Vertex vertex1 : vertex.getEdgesList()){
                System.out.println(vertex1.getBuilding());
            }
        }
        Graph graph = new Graph();
        System.out.println("Source : " + source);
        Vertex srcVertex = getVertex(source, buildingList);
        System.out.println("Source building : " + srcVertex.getBuilding());
        Vertex destVertex = getVertex(destination, buildingList);

        graph.checkBuildingIsFound(srcVertex, destVertex, source, destination);

        HashMap<String, Integer> distances = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();

        //loop on buildingList and set the distance of each building to infinity
        for (Vertex vertex : buildingList) {
            distances.put(vertex.getBuilding(), Integer.MAX_VALUE);
            previous.put(vertex.getBuilding(), null);
        }

        //set the distance of the source building to 0
        distances.put(source, 0);

        //Create priority queue to store the buildings and their distances
        PriorityQueue<NodePath> queue = new PriorityQueue<>();
        queue.add(new NodePath(source, 0));
        while (!queue.isEmpty()){
            NodePath current = queue.poll();
            String currentNode = current.getNode();
            double currentDistance = current.getDistance();

            if (currentNode.equals(destination)) {
                // Destination reached, construct the shortest path
                System.out.println("if state");
                List<String> path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(0, currentNode);
                    currentNode = previous.get(currentNode);
                }
                return path;
            }
        }

        return null;

    }

    private Vertex getVertex(String buildingName, List<Vertex> buildingList) {
        System.out.println("GETTT VETETTESXX");


        System.out.println(buildingList.size());

        for (Vertex vertex : buildingList) {
            System.out.println("Vertex: " + vertex.getBuilding() );
            if (vertex.getBuilding().equals(buildingName)) {
                return vertex;
            }
        }
        return null;
    }
}
