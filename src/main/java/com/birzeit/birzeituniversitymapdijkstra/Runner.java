package com.birzeit.birzeituniversitymapdijkstra;

import com.birzeit.birzeituniversitymapdijkstra.model.Edge;
import com.birzeit.birzeituniversitymapdijkstra.model.Graph;
import com.birzeit.birzeituniversitymapdijkstra.model.Vertex;
import com.birzeit.birzeituniversitymapdijkstra.service.Dijkstra;
import com.birzeit.birzeituniversitymapdijkstra.service.GraphReader;

import java.util.List;

public class Runner {

    public static void main(String[] args) throws Exception {

        GraphReader graphReader = new GraphReader();
        graphReader.readGraphFromFile("buildings.txt");
        //graph.printGraph();
        System.out.println("---------------------");
        Dijkstra dijkstra = new Dijkstra();
        List<String> list = dijkstra.findShortestPath(Graph.buildingList , "shaeen", "shaeen");
        //loop on list string
        for (String s : list) {
            System.out.println("F");
            System.out.println(s);
        }







    }

}
