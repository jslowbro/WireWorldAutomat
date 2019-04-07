package com.wireworld;

public class OR extends WireStructure {
    public OR(Coordinates coordinates, char orientation) {
        super(coordinates, orientation);
        // Wymiary oraz wygląd bramki logicznej, będzie zależał od jej położenia pionowego lub poziomego
        switch (orientation) {
            case 'N':
                grid = new char[][]{{'1', '1', '1'}, {'0', '1', '0'}};
                size.x = 3;
                size.y = 2;
                break;
            case 'S':
                grid = new char[][]{{'0', '1', '0'}, {'1', '1', '1'}};
                size.x = 3;
                size.y = 2;
                break;
            case 'W':
                grid = new char[][]{{'1', '0'}, {'1', '1'}, {'1', '0'}};
                size.x = 2;
                size.y = 3;
                break;
            case 'E':
                grid = new char[][]{{'0', '1'}, {'1', '1'}, {'0', '1'}};
                size.x = 2;
                size.y = 3;
                break;
            default:
                System.out.println("Cos poszlo nie tak w orientacji struktury");
                break;
        }
    }

}
