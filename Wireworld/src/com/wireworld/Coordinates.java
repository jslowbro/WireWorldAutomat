package com.wireworld;


public class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y, boolean illegal) {
        if (illegal) {
            this.x = x;
            this.y = y;
        }
    }

    public Coordinates(int x, int y) {
        if (argCheck(x, y)) {
            this.x = x;
            this.y = y;
        }
    }

    public Coordinates(String cord) {
        //Cord jest w następującej postaci : (10,10)
        cord = cord.replace("(", "");
        cord = cord.replace(")", "");
        String[] integers;
        integers = cord.split(",");
        if (argCheck(Integer.parseInt(integers[0]), Integer.parseInt(integers[1]))) {
            this.x = Integer.parseInt(integers[0]);
            this.y = Integer.parseInt(integers[1]);
        }
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    private static boolean argCheck(int x, int y) {
        if (x >= 0 && x <= Options.getInstanceOf().getSize().x + 1 && y >= 0 && x <= Options.getInstanceOf().getSize().y + 1) {
            return true;
        }
        throw new AssertionError("Nieprawidłowe koordynaty = " + new Coordinates(x, y, true).toString());
    }


}
