package com.wireworld;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @Test
    public void argCheck() {
        Options.getInstanceOf().setSize(new Coordinates(25,25,true));
        assertTrue(Coordinates.argCheck(1,1));
        //assertFalse(Coordinates.argCheck(-10,0));
        assertTrue(Coordinates.argCheck(0,0));
        //assertFalse(Coordinates.argCheck(27,0));
    }
}