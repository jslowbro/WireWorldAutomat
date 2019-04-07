package com.wireworld;

public class XOR extends WireStructure {
    public XOR(Coordinates coordinates, char orientation) {
        super(coordinates, orientation);
        // Wymiary oraz wygląd bramki logicznej, będzie zależał od jej położenia pionowego lub poziomego
        switch (orientation) {
            case 'N':
                grid = new char[][]{{'0', '1', '1', '1', '0'}, {'0', '1', '0', '1', '0'}, {'1', '1', '0', '1', '1'}, {'0', '1', '1', '1', '0'}};
                size.x = 5;
                size.y = 4;
                break;
            case 'S':
                grid = new char[][]{{'0', '1', '1', '1', '0'}, {'1', '1', '0', '1', '1'}, {'0', '1', '0', '1', '0'}, {'0', '1', '1', '1', '0'}};
                size.x = 5;
                size.y = 4;
                break;
            case 'W':
                grid = new char[][]{{'0', '0', '1', '0'}, {'1', '1', '1', '1'}, {'1', '0', '0', '1'}, {'1', '1', '1', '1'}, {'0', '0', '1', '0'}};
                size.x = 4;
                size.y = 5;
                break;
            case 'E':
                grid = new char[][]{{'0', '1', '0', '0'}, {'1', '1', '1', '1'}, {'1', '0', '0', '1'}, {'1', '1', '1', '1'}, {'0', '1', '0', '0'}};
                size.x = 4;
                size.y = 5;
                break;
            default:
                System.out.println("Cos poszlo nie tak w orientacji struktury");
                break;
        }
    }

}
