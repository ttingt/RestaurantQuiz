package ca.ubc.cs.cpsc210.quiz.model.tests;

import ca.ubc.cs.cpsc210.quiz.model.LatLng;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LatLngTest {

    private static final double DELTA = 1e-15;
    private LatLng latLng1;
    private LatLng latLng2;

    @Before
    public void runBefore() {
        latLng1 = new LatLng(12,34);
        latLng2 = new LatLng(100.113, 87.65);
    }

    @Test
    public void testGetLat() {
        assertEquals(12, latLng1.getLat(), DELTA);
        assertEquals(100.113, latLng2.getLat(), DELTA);
    }

    @Test
    public void testGetLng() {
        assertEquals(34, latLng1.getLng(), DELTA);
        assertEquals(87.65, latLng2.getLng(), DELTA);
    }
}