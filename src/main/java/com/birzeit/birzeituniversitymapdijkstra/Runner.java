package com.birzeit.birzeituniversitymapdijkstra;

import com.birzeit.birzeituniversitymapdijkstra.model.Edge;
import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;
import com.birzeit.birzeituniversitymapdijkstra.service.GraphReader;

public class Runner {

    public static void main(String[] args) throws Exception {

        //GraphReader graphReader = new GraphReader();
        //Graph graph = graphReader.readGraphFromFile("buildings.txt");
        //graph.printGraph();

        Graph graph = new Graph();
        graph.addVertex("a", 0, 0);
        graph.addVertex("b", 0, 0);
        graph.addVertex("c", 0, 0);
        graph.addVertex("d", 0, 0);
        graph.addVertex("e", 0, 0);
        graph.addVertex("f", 0, 0);
        graph.addVertex("g", 0, 0);
        graph.addVertex("h", 0, 0);
        //add edges
        graph.addAdjacent("a", "b", 4);
        graph.addAdjacent("a", "c", 8);
        graph.addAdjacent("b", "c", 11);
        graph.addAdjacent("b", "d", 8);
        graph.addAdjacent("c", "e", 7);

        graph.printGraph();





    }

}
