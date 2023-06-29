package com.birzeit.birzeituniversitymapdijkstra.service;

import com.birzeit.birzeituniversitymapdijkstra.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public DijkstraResult findShortestPath(List<Vertex> buildingList, String source, String destination) {

        System.out.println(buildingList.size());

        //loop on buildingList and print the buildings
        for (Vertex vertex : buildingList) {
            System.out.println(vertex.getBuilding());
            for (Vertex vertex1 : vertex.getEdgesList()){
                System.out.println(vertex1.getBuilding());
            }
        }
        Graph graph = new Graph();
        Vertex srcVertex = getVertex(source, buildingList);
        Vertex destVertex = getVertex(destination, buildingList);

        graph.checkBuildingIsFound(srcVertex, destVertex, source, destination);

        HashMap<String, Double> distances = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();

        //loop on buildingList and set the distance of each building to infinity
        for (Vertex vertex : buildingList) {
            distances.put(vertex.getBuilding(), Double.MAX_VALUE);
            previous.put(vertex.getBuilding(), null);
        }

        //set the distance of the source building to 0
        distances.put(source, 0.0);

        //Create priority queue to store the buildings and their distances
        PriorityQueue<NodePath> queue = new PriorityQueue<>();
        queue.add(new NodePath(source, 0));
        while (!queue.isEmpty()){
            System.out.println("while state");
            NodePath current = queue.poll();
            System.out.println("Current: " + current.getNode());
            String currentNode = current.getNode();
            double currentDistance = current.getDistance();

            if (currentNode.trim().equalsIgnoreCase(destination)) {
                // Destination reached, construct the shortest path

                System.out.println("if state");
                List<String> path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(0, currentNode);
                    currentNode = previous.get(currentNode);
                }

                double distance = distances.get(destination);

                return new DijkstraResult(path, distance);
            }

            // Visit all neighboring nodes
            List<Vertex> neighbors = getVertex(currentNode, buildingList).getEdgesList();
            if (neighbors != null) {
                for (Vertex neighbor : neighbors) {
                    String neighborNode = neighbor.getBuilding();
                    double neighborDistance = neighbor.getDistance();
                    double newDistance = currentDistance + neighborDistance;

                    if (newDistance < distances.get(neighborNode)) {
                        // Update the distance and previous node
                        distances.put(neighborNode, newDistance);
                        previous.put(neighborNode, currentNode);

                        // Add the neighbor to the priority queue
                        queue.add(new NodePath(neighborNode, newDistance));
                    }
                }
            }

        }

        return null;

    }

    private Vertex getVertex(String buildingName, List<Vertex> buildingList) {

        for (Vertex vertex : buildingList) {
            if (vertex.getBuilding().equalsIgnoreCase(buildingName)) {
                return vertex;
            }
        }
        return null;
    }
}
