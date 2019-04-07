package com.wireworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class WireStructureTest {

    @Test
    public void getCoordinates() {
        WireStructure wireStructure = new AND(new Coordinates(2,2,true),'S');
        assertEquals(new Coordinates(2,2,true).x, wireStructure.getCoordinates().x);
        assertEquals(new Coordinates(2,2,true).y, wireStructure.getCoordinates().y);
        assertNotEquals(new Coordinates(2,2,true), wireStructure.getCoordinates()); //ciekawy test bardzo
    }

    @Test
    public void getClassConverson() {
        WireStructure wireStructure = new AND(new Coordinates(2,2,true), 'N');
        assertEquals("AND", wireStructure.getClassConversion(wireStructure.getClass().toString()));
    }

}