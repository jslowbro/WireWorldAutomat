package com.wireworld;

public class Wire {
    private Coordinates startCoordinates;
    private Coordinates endCoordinates;


    Wire(Coordinates startCoordinates, Coordinates endCoordinates) {
        this.startCoordinates = startCoordinates;
        this.endCoordinates = endCoordinates;

    }

    public Coordinates getStartCoordinates() {
        return startCoordinates;
    }

    public Coordinates getEndCoordinates() {
        return endCoordinates;
    }

    @Override
    public String toString() {
        return "WIRE " + getStartCoordinates() + " " + getEndCoordinates();
    }
}
