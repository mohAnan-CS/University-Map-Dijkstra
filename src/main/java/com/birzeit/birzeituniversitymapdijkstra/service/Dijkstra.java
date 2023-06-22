package com.birzeit.birzeituniversitymapdijkstra.service;

import com.birzeit.birzeituniversitymapdijkstra.model.Edge;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public List<String> findShortestPath(HashMap<String, List<Edge>> buildingHashMap, String source, String destination) {

        HashMap<String, Integer> distances = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();

        //loop on hashmap and set the distance of each building to infinity
        for (String node : buildingHashMap.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }

        //set the distance of the source building to 0
        distances.put(source, 0);

        //Create priority queue to store the buildings and their distances
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(source, 0, 0));

        return null;

    }
}
