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

import static org.junit.Assert.*;

public class GoogleDirectionsParserTestOneLegOneStep {
    private static final double TOL = 1.0e-6;
    private static final String FILEPATH = "./testdata/one-leg-one-step.json";
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
        assertEquals(82, rte.getDistance());
    }

    @Test
    public void testNumPoints() {
        Leg l = rte.getLegs().get(0);

        assertEquals(3, l.getPoints().size());
    }

    @Test
    public void testNumLegs() {
        assertEquals(1, rte.getLegs().size());
    }

    @Test
    public void testPoints() {
        LatLng pt1 = new LatLng(49.263490, -123.248620);
        LatLng pt2 = new LatLng(49.26377, -123.248850);
        LatLng pt3 = new LatLng(49.264140, -123.249150);

        Leg leg = rte.getLegs().get(0);
        List<LatLng> pts = leg.getPoints();

        // check lat/lon of all 3 points
        assertEquals(pt1.getLat(), pts.get(0).getLat(), TOL);
        assertEquals(pt1.getLng(), pts.get(0).getLng(), TOL);

        assertEquals(pt2.getLat(), pts.get(1).getLat(), TOL);
        assertEquals(pt2.getLng(), pts.get(1).getLng(), TOL);

        assertEquals(pt3.getLat(), pts.get(2).getLat(), TOL);
        assertEquals(pt3.getLng(), pts.get(2).getLng(), TOL);
    }
}
