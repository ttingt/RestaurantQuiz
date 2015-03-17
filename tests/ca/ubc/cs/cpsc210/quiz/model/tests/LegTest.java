package ca.ubc.cs.cpsc210.quiz.model.tests;

import ca.ubc.cs.cpsc210.quiz.model.LatLng;
import ca.ubc.cs.cpsc210.quiz.model.Leg;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LegTest {

    private Leg leg;

    @Before
    public void runBefore() {
        leg = new Leg();
    }

    @Test
    public void testAddPoint() {
        assertTrue(leg.getPoints().isEmpty());

        // add one point
        LatLng pt = new LatLng(1.23, 4.56);
        leg.addPoint(pt);
        assertTrue(leg.getPoints().size() == 1);
        assertTrue(leg.getPoints().contains(pt));

        // add second point
        LatLng pt2 = new LatLng(7.89, 0.12);
        leg.addPoint(pt2);
        assertTrue(leg.getPoints().size() == 2);
        assertTrue(leg.getPoints().get(1).equals(pt2));
    }

    @Test
    public void testAddAllPoints() {
        LatLng pt1 = new LatLng(1.23, 4.56);
        LatLng pt2 = new LatLng(7.89, 0.12);
        List<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);

        leg.addAllPoints(pts);
        assertTrue(leg.getPoints().size() == 2);
        assertTrue(leg.getPoints().get(0).equals(pt1));
        assertTrue(leg.getPoints().get(1).equals(pt2));
    }

    @Test
    public void testGetPoints() {
        // pts has no points
        List<LatLng> pts = leg.getPoints();
        assertEquals(0, pts.size());

        // pts has 1 point
        LatLng pt1 = new LatLng(1.23, 4.56);
        leg.addPoint(pt1);
        pts = leg.getPoints();
        assertEquals(1, pts.size());
        assertTrue(pts.get(0).equals(pt1));

        // pts has 2 points
        LatLng pt2 = new LatLng(2.34, 5.67);
        leg.addPoint(pt2);
        pts = leg.getPoints();
        assertEquals(2, pts.size());
        assertTrue(pts.get(1).equals(pt2));
    }

    @Test
    public void testSetDistance() {
        leg.setDistance(10);
        assertEquals(10, leg.getDistance());
        leg.setDistance(500);
        assertEquals(500, leg.getDistance());
    }

    @Test
    public void testGetDistance() {
        assertEquals(0, leg.getDistance());
        leg.setDistance(100);
        assertEquals(100, leg.getDistance());
    }
}