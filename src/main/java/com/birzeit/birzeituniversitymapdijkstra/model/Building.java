package com.birzeit.birzeituniversitymapdijkstra.model;

public class Building {

    private String buildingName;
    private double xCoordinate;
    private double yCoordinate;

    public Building(String buildingName, double xCoordinate, double yCoordinate) {
        this.buildingName = buildingName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingName='" + buildingName + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
