package com.wireworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class WireTest {

    @Test
    public void getStartCoordinates() {
        Wire wire = new Wire(new Coordinates(1,1,true),new Coordinates(1,10,true));
        assertNotEquals(new Coordinates(1,1,true),wire.getStartCoordinates());
        assertEquals(new Coordinates(1,1,true).y,wire.getStartCoordinates().y);
    }
}