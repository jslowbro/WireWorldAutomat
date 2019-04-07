package com.wireworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class IOManagerTest {

    @Test
    public void oneMove() {
        Matrix matrix = new Matrix();
        IOManager.oneMove(matrix);
        assertEquals(IOManager.genNumber, 1);
    }

    @Test
    public void isLegalWireStructure() {
        Matrix matrix = new Matrix();
        matrix.setSize(new Coordinates(3,3,true));
        AND and = new AND(new Coordinates(1,1,true),'S');
        assertFalse(IOManager.isLegalWireStructure(matrix,and));

    }

}