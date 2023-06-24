package com.birzeit.birzeituniversitymapdijkstra.model;

public class NodePath implements Comparable<NodePath>{


        private String node;
        private double distance;

        public NodePath(String node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        public String getNode() {
            return node;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public int compareTo(NodePath other) {
            return Double.compare(distance, other.getDistance());
        }

}
