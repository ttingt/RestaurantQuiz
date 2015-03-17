package ca.ubc.cs.cpsc210.quiz.google.tests;

import ca.ubc.cs.cpsc210.quiz.google.GoogleDirectionsParser;
import ca.ubc.cs.cpsc210.quiz.model.LatLng;
import ca.ubc.cs.cpsc210.quiz.model.Leg;
import ca.ubc.cs.cpsc210.quiz.model.Route;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GoogleDirectionsParserTestRebootToChanViaNitobe2Legs {
    private static final double TOL = 1.0e-6;
    private static final String FILEPATH = "./testdata/reboot-to-chan-via-nitobe-2-legs.json";
    private static StringBuilder jsonResponse;
    private static Route rte;

    @BeforeClass
    public static void runBefore() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILEPATH));
            jsonResponse = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                jsonResponse.append(str);
            }
            in.close();
            parseDirections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseDirections() {
        try {
            rte = GoogleDirectionsParser.parseRoute(jsonResponse.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Basic parsing failed");
        }
    }

    @Test
    public void testDistance() {
        assertEquals(2214, rte.getDistance());
    }

    @Test
    public void testNumPoints() {
        int numPoints = 0;

        for (Leg leg : rte.getLegs()) {
            numPoints += leg.getPoints().size();
        }

        assertEquals(143, numPoints);
    }

    @Test
    public void testNumLegs() {
        assertEquals(2, rte.getLegs().size());
    }

    @Test
    public void testPoints() {
        LatLng leg1pt1 = new LatLng(49.2614500, -123.247010);
        LatLng leg1pt2 = new LatLng(49.2654400, -123.256210);
        LatLng leg1pt3 = new LatLng(49.2665600, -123.260310);
        LatLng leg2pt1 = new LatLng(49.2665600, -123.260310);
        LatLng leg2pt2 = new LatLng(49.2690300, -123.255970);
        LatLng leg2pt3 = new LatLng(49.2692400, -123.255280);

        List<Leg> legs = rte.getLegs();

        // check first leg
        Leg leg1 = legs.get(0);
        List<LatLng> pts = leg1.getPoints();

        // check first point
        assertEquals(leg1pt1.getLat(), pts.get(0).getLat(), TOL);
        assertEquals(leg1pt1.getLng(), pts.get(0).getLng(), TOL);

        // check point in the middle
        assertEquals(leg1pt2.getLat(), pts.get(31).getLat(), TOL);
        assertEquals(leg1pt2.getLng(), pts.get(31).getLng(), TOL);

        // check last point
        assertEquals(leg1pt3.getLat(), pts.get(69).getLat(), TOL);
        assertEquals(leg1pt3.getLng(), pts.get(69).getLng(), TOL);


        // check second leg
        Leg leg2 = legs.get(1);
        pts = leg2.getPoints();

        // check first point
        assertEquals(leg2pt1.getLat(), pts.get(0).getLat(), TOL);
        assertEquals(leg2pt1.getLng(), pts.get(0).getLng(), TOL);

        // check point in the middle
        assertEquals(leg2pt2.getLat(), pts.get(67).getLat(), TOL);
        assertEquals(leg2pt2.getLng(), pts.get(67).getLng(), TOL);

        // check last point
        assertEquals(leg2pt3.getLat(), pts.get(72).getLat(), TOL);
        assertEquals(leg2pt3.getLng(), pts.get(72).getLng(), TOL);
    }
}