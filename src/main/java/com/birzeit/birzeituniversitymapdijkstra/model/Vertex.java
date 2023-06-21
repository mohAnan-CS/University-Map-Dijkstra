package com.birzeit.birzeituniversitymapdijkstra.model;

public class Vertex {

    private String building;
    private int distance;

    public Vertex(String building, int distance) {
        this.building = building;
        this.distance = distance;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "building='" + building + '\'' +
                ", distance=" + distance +
                '}';
    }
}
