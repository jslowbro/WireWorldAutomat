package com.wireworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void insertWires() {
        Matrix matrix = new Matrix();
        matrix.getWires().add(new Wire(new Coordinates(1,1,true),new Coordinates(1,10,true)));
        matrix.setSize(new Coordinates(27,27,true));
        matrix.createBlankGrid();
        matrix.InsertWires();
        assertEquals(matrix.getGrid()[2][2],'1');
        assertEquals(matrix.getGrid()[3][2],'0');
    }

    @Test
    public void insertHeadsNTails() {
        Matrix matrix = new Matrix();
        matrix.setSize(new Coordinates(27,27,true));
        matrix.getWires().add(new Wire(new Coordinates(1,1,true),new Coordinates(1,10,true)));
        matrix.getElectronTails().add(new Coordinates(1,5,true));
        matrix.createBlankGrid();
        matrix.InsertWires();
        matrix.InsertHeadsNTails();
        assertEquals(matrix.getGrid()[2][6],'3');
        assertNotEquals(matrix.getGrid()[2][7],'3');
    }
}