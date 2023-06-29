package com.birzeit.birzeituniversitymapdijkstra.model;

import java.util.List;

public class DijkstraResult {

    private List<String> path;
    private double distance;

    public DijkstraResult(List<String> path, double distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DijkstraResult{" +
                "path=" + path +
                ", distance=" + distance +
                '}';
    }
}
