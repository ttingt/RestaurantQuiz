package ca.ubc.cs.cpsc210.quiz.yelp.tests;


import ca.ubc.cs.cpsc210.quiz.model.*;
import ca.ubc.cs.cpsc210.quiz.yelp.YelpDataParser;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/*
 * Tests for YelpDataParser where the JSON response includes restaurants not matching
 * the yelpCityFilter city name and some are closed.
 */

public class YelpDataParserTestVersion2 {
    private static final String FILEPATH = "./testdata/yelp-restaurants-multiple-v2.json";
    private static StringBuilder jsonResponse;
    private static List<Restaurant> restaurants;

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
            parseRestaurants();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseRestaurants() {
        try {
            restaurants = YelpDataParser.parseRestaurantData(jsonResponse.toString(), "Vancouver");
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Basic parsing failed");
        }
    }

    @Test
    public void testNumRestaurants() {
        assertEquals(5, restaurants.size());
    }

    @Test
    public void testSkipsClosedAndNonVancouverRestaurants() {
        // First restaurant in parsed list
        Restaurant r3 = restaurants.get(0);

        assertEquals("Fliptop Filipino Fusion", r3.getName());
        List<Category> categories3 = new ArrayList<Category>();
        categories3.add(new Category("Food Trucks", "foodtrucks"));
        categories3.add(new Category("Filipino", "filipino"));
        assertEquals(categories3, r3.getCategories());
        assertEquals("fliptop-filipino-fusion-vancouver", r3.getId());
        assertEquals("Granville St and W 10th Ave", r3.getAddress());
        assertEquals("V6H", r3.getPostalCode());
        GeoArea geoArea1 = new Neighbourhood("Fairview Slopes", new City("Vancouver", "BC", "CA"));
        assertEquals(geoArea1, r3.getGeographicalArea());

        // Third restaurant in parsed list
        Restaurant r6 = restaurants.get(2);

        assertEquals("Edible Canada At the Market", r6.getName());

        // Last restaurant in parsed list
        Restaurant r8 = restaurants.get(4);

        assertEquals("Fable", r8.getName());
    }

    @Test
    public void testEmptyStringPostalCode() {
        Restaurant r4 = restaurants.get(1);
        assertTrue(r4.getPostalCode().equals(""));
    }

    @Test
    public void testNoPostalCodeKey() {
        Restaurant r6 = restaurants.get(2);
        assertTrue(r6.getPostalCode() == null);
    }

    @Test
    public void testMultipleAddresses() {
        Restaurant r8 = restaurants.get(4);
        assertEquals("123 ABC St", r8.getAddress());
    }

    @Test
    public void testEmptyNeighbourhoodGeoAreaIsCity() {
        Restaurant r7 = restaurants.get(3);
        assertTrue(r7.getGeographicalArea() instanceof City);
        GeoArea geoArea7 = new City("Vancouver", "BC", "CA");
        assertEquals(geoArea7, r7.getGeographicalArea());
    }

    @Test
    public void testNoNeighbourhoodGeoAreaIsCity() {
        Restaurant r6 = restaurants.get(2);
        assertEquals(new City("Vancouver", "BC", "CA"), r6.getGeographicalArea());
    }

}
