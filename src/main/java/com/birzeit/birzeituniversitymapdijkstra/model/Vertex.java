package com.birzeit.birzeituniversitymapdijkstra.model;

import java.util.List;
import java.util.Objects;

public class Vertex {

    private String building;
    private double xCoordinate, yCoordinate;
    private double distance, weight;
    private int index;
    private List<Vertex> edgesList;

    public Vertex(String building, double xCoordinate, double yCoordinate) {
        this.building = building;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Vertex(String building, double distance, double weight, List<Vertex> edgesList) {
        this.building = building;
        this.distance = distance;
        this.weight = weight;
        this.edgesList = edgesList;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<Vertex> getEdgesList() {
        return edgesList;
    }

    public void setEdgesList(List<Vertex> edgesList) {
        this.edgesList = edgesList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "building='" + building + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", distance=" + distance +
                ", weight=" + weight +
                '}';
    }

    // Override the equals method to compare the contents of the vertices
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertex other = (Vertex) obj;
        return building.equals(other.building) && xCoordinate == other.xCoordinate && yCoordinate == other.yCoordinate;
    }

    // Override the hashCode method to be consistent with the equals method
    @Override
    public int hashCode() {
        return Objects.hash(building, xCoordinate, yCoordinate);
    }
}
