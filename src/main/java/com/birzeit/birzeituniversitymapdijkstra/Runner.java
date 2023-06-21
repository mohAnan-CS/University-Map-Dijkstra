package com.birzeit.birzeituniversitymapdijkstra;

import com.birzeit.birzeituniversitymapdijkstra.model.Edge;
import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;
import com.birzeit.birzeituniversitymapdijkstra.service.GraphReader;

public class Runner {

    public static void main(String[] args) throws Exception {

        GraphReader graphReader = new GraphReader();
        Graph graph = graphReader.readGraphFromFile("buildings.txt");
        graph.printGraph();




    }

}
