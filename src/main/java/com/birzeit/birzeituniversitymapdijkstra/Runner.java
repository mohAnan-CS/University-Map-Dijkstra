package com.birzeit.birzeituniversitymapdijkstra;

import com.birzeit.birzeituniversitymapdijkstra.model.Edge;
import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;

public class Runner {

    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex("A", 0, 0);
        graph.addVertex("B", 0, 0);
        graph.addVertex("C", 0, 0);
        graph.addAdjacent("A", "B", -1);
        graph.addAdjacent("A", "C", 10);
        graph.addAdjacent("A", "C", 3);
        graph.printGraph();





    }

}
