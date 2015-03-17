package ca.ubc.cs.cpsc210.quiz.model.tests;


import ca.ubc.cs.cpsc210.quiz.model.City;
import ca.ubc.cs.cpsc210.quiz.model.Neighbourhood;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class CityTest {

    private City city1;
    private City city2;

    @Before
    public void setUp() {
        city1 = new City("Vancouver", "BC", "CA");
        city2 = new City("Calgary", "AB", "CA");
    }

    @Test
    public void testConstructor() {
        try {
            City cityA = new City("Vancouver", "ABC", "CA");
            fail("IllegalValueException should have been thrown");
        } catch (IllegalArgumentException e) {}
        try {
            City cityB = new City("Richmond", "BC", "CAN");
            fail("\"IllegalValueException should have been thrown");
        } catch (IllegalArgumentException e) {}
        try {
            City cityC = new City("Langley", "ABC", "CAN");
            fail("IllegalValueException should have been thrown");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public void testGetGeographicalString() {
        assertEquals("Vancouver, BC, CA", city1.getCityString());
        assertEquals("Calgary, AB, CA", city2.getCityString());
    }

    @Test
    public void testGetAllGeoStrings() {
        List<String> geoStrings = city1.getAllGeoStrings();
        assertEquals(1, geoStrings.size());
        assertEquals("Vancouver, BC, CA", geoStrings.get(0));
    }

    @Test
    public void testEqualsTrue() {
        assertTrue(city1.equals(city1));
        assertTrue(city2.equals(city2));

        assertTrue(city1.equals(new City("Vancouver", "BC", "CA")));
        assertTrue(city2.equals(new City("Calgary", "AB", "CA")));

        // Neighbourhood with same city
        assertTrue(city1.equals(new Neighbourhood("Happy", city1)));
        assertTrue(city1.equals(new Neighbourhood("Happy", new City("Vancouver", "BC", "CA"))));
    }

    @Test
    public void testEqualsFalse() {
        assertFalse(city1.equals(null));
        assertFalse(city1.equals("abcde"));
        assertFalse(city1.equals(12345));

        // Entirely different city
        assertFalse(city1.equals(city2));
        // Different city name
        assertFalse(city1.equals(new City("Richmond", "BC", "CA")));
        // Different province/state
        assertFalse(city1.equals(new City("Vancouver", "AB", "CA")));
        // Different country code
        assertFalse(city1.equals(new City("Vancouver", "BC", "US")));

        // Neighbourhood with different city
        assertFalse(city1.equals(new Neighbourhood("Dumpy", city2)));
    }

    @Test
    public void testHashCode() {
        City testCity = new City("Vancouver", "BC", "CA");
        assertTrue(city1.equals(testCity));
        assertEquals(city1.hashCode(), testCity.hashCode());

        Neighbourhood nbhd = new Neighbourhood("Happy", testCity);
        assertEquals(city1.hashCode(), nbhd.hashCode());
    }
}
