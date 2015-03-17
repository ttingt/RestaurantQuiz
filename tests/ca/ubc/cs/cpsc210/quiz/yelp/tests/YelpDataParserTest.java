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

import static org.junit.Assert.*;

/*
 * Tests for YelpDataParser where the JSON response included only restaurants matching
 * the yelpCityFilter city name and are all not closed.
 */

public class YelpDataParserTest {
    private static final String FILEPATH = "./testdata/yelp-restaurants-multiple.json";
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
        assertEquals(10, restaurants.size());
    }

    @Test
    public void testFirstRestaurant() {
        Restaurant r1 = restaurants.get(0);

        assertEquals("Heirloom Vegetarian", r1.getName());
        List<Category> categories1 = new ArrayList<Category>();
        categories1.add(new Category("Breakfast & Brunch", "breakfast_brunch"));
        categories1.add(new Category("Vegetarian", "vegetarian"));
        categories1.add(new Category("Vegan", "vegan"));
        assertEquals(categories1, r1.getCategories());
        assertEquals("heirloom-vegetarian-vancouver", r1.getId());
        assertEquals("1509 W 12th Avenue", r1.getAddress());
        assertEquals("V6J 2E1", r1.getPostalCode());
        // Multiple neighbourhoods in JSONArray
        GeoArea geoArea1 = new Neighbourhood("Fairview Slopes", new City("Vancouver", "BC", "CA"));
        assertEquals(geoArea1, r1.getGeographicalArea());
    }

    @Test
    public void testFifthRestaurant() {
        Restaurant r5 = restaurants.get(4);

        assertEquals("La Taqueria Pinche Taco Shop", r5.getName());
        List<Category> categories5 = new ArrayList<Category>();
        categories5.add(new Category("Mexican", "mexican"));
        assertEquals(categories5, r5.getCategories());
        assertEquals("la-taqueria-pinche-taco-shop-vancouver", r5.getId());
        assertEquals("2549 Cambie Street", r5.getAddress());
        assertEquals("V5Z 3V6", r5.getPostalCode());
        GeoArea geoArea5 = new Neighbourhood("Fairview Slopes", new City("Vancouver", "BC", "CA"));
        assertEquals(geoArea5, r5.getGeographicalArea());
    }

    @Test
    public void testLastRestaurant() {
        Restaurant r10 = restaurants.get(9);

        assertEquals("Hawkers Delight Deli", r10.getName());
        List<Category> categories10 = new ArrayList<Category>();
        categories10.add(new Category("Malaysian", "malaysian"));
        categories10.add(new Category("Singaporean", "singaporean"));
        assertEquals(categories10, r10.getCategories());
        assertEquals("hawkers-delight-deli-vancouver", r10.getId());
        assertEquals("4127 Main Street", r10.getAddress());
        assertEquals("V5V 3P6", r10.getPostalCode());
        GeoArea geoArea10 = new Neighbourhood("Riley Park", new City("Vancouver", "BC", "CA"));
        assertEquals(geoArea10, r10.getGeographicalArea());
    }

}
