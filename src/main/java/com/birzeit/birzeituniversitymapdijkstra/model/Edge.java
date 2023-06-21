package com.birzeit.birzeituniversitymapdijkstra.model;

public class Edge {

    private String sourceBuilding;
    private String destinationBuilding;
    private int distance;

    public Edge(String sourceBuilding, String destinationBuilding, int distance) {
        this.sourceBuilding = sourceBuilding;
        this.destinationBuilding = destinationBuilding;
        this.distance = distance;
    }

    public String getSourceBuilding() {
        return sourceBuilding;
    }

    public void setSourceBuilding(String sourceBuilding) {
        this.sourceBuilding = sourceBuilding;
    }

    public String getDestinationBuilding() {
        return destinationBuilding;
    }

    public void setDestinationBuilding(String destinationBuilding) {
        this.destinationBuilding = destinationBuilding;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "sourceBuilding='" + sourceBuilding + '\'' +
                ", destinationBuilding='" + destinationBuilding + '\'' +
                ", distance=" + distance +
                '}';
    }
}
