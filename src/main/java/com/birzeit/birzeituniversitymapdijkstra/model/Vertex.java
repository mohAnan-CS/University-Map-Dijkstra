package com.birzeit.birzeituniversitymapdijkstra.model;

import java.util.Objects;

public class Vertex {

    private String building;
    private double xCoordinate, yCoordinate;

    public Vertex(String building, double xCoordinate, double yCoordinate) {
        this.building = building;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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

    @Override
    public String toString() {
        return "Vertex{" +
                "building='" + building + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
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
