package ca.ubc.cs.cpsc210.quiz.model.tests;

import ca.ubc.cs.cpsc210.quiz.model.LatLng;
import ca.ubc.cs.cpsc210.quiz.model.Leg;
import ca.ubc.cs.cpsc210.quiz.model.Route;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RouteTest {

    private Route route;

    @Before
    public void setUp() {
        route = new Route();
    }

    @Test
    public void testAddLeg() {
        Leg leg = new Leg();
        leg.addPoint(new LatLng(1.22, 3.44));
        route.addLeg(leg);
        assertEquals(1, route.getLegs().size());
        assertTrue(route.getLegs().get(0).equals(leg));
    }

    @Test
    public void testGetLegs() {
        assertTrue(route.getLegs().isEmpty());

        Leg leg1 = new Leg();
        Leg leg2 = new Leg();
        leg1.addPoint(new LatLng(1.22, 3.44));
        leg2.addPoint(new LatLng(2.33, 4.55));
        route.addLeg(leg1);
        route.addLeg(leg2);
        List<Leg> legs = route.getLegs();
        assertEquals(2, legs.size());
        assertTrue(legs.get(0).equals(leg1));
        assertTrue(legs.get(1).equals(leg2));
    }

    @Test
    public void testGetDistance() {
        assertEquals(0, route.getDistance());

        Leg leg1 = new Leg();
        Leg leg2 = new Leg();
        leg1.setDistance(100);
        leg2.setDistance(200);
        route.addLeg(leg1);
        assertEquals(100, route.getDistance());
        route.addLeg(leg2);
        assertEquals(300, route.getDistance());
    }
}