package com.wireworld;

public abstract class WireStructure {
    Coordinates size;
    private Coordinates coordinates;
    private char orientation;
    char[][] grid;

    WireStructure(Coordinates coordinates, char orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
        this.size = new Coordinates(0, 0, true);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    private char getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return getClassConversion(this.getClass().toString()) + " " + getOrientation() + " " + getCoordinates().toString();
    }

    private String getClassConversion(String s) {
        return s.replaceAll("class com.wireworld.", "").toUpperCase();
    }
}

