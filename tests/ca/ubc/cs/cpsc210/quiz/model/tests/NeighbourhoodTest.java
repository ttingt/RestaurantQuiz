package ca.ubc.cs.cpsc210.quiz.model.tests;


import ca.ubc.cs.cpsc210.quiz.model.City;
import ca.ubc.cs.cpsc210.quiz.model.Neighbourhood;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class NeighbourhoodTest {

    City city;
    Neighbourhood nbhd;

    @Before
    public void setU() {
        city = new City("Vancouver", "BC", "CA");
        nbhd = new Neighbourhood("Fancy Slopes East Bank", city);
    }

    @Test
    public void testGetCity() {
        assertTrue(city.equals(nbhd.getCity()));
    }

    @Test
    public void testGetCityString() {
        assertEquals("Vancouver, BC, CA", nbhd.getCityString());
    }

    @Test
    public void testGetAllGeoStrings() {
        List<String> geoStrings = nbhd.getAllGeoStrings();
        assertEquals(4, geoStrings.size());

        assertEquals("Fancy, Vancouver, BC, CA", geoStrings.get(0));
        assertEquals("Fancy Slopes, Vancouver, BC, CA", geoStrings.get(1));
        assertEquals("Fancy Slopes East, Vancouver, BC, CA", geoStrings.get(2));
        assertEquals("Fancy Slopes East Bank, Vancouver, BC, CA", geoStrings.get(3));
    }

    @Test
    public void testEqualsTrue() {
        City testCity = new City("Vancouver", "BC", "CA");
        Neighbourhood testNeighbourhood = new Neighbourhood("Fancy Slopes East Bank", testCity);

        assertTrue(nbhd.equals(nbhd));
        assertTrue(nbhd.equals(testNeighbourhood));
        // Different neighbourhood name
        assertTrue(nbhd.equals(new Neighbourhood("Unhappy", city)));

        // City object
        assertTrue(nbhd.equals(testCity));
    }

    @Test
    public void testEqualsFalse() {
        City testCity = new City("Boo", "AB", "CD");
        Neighbourhood testNbhd = new Neighbourhood("Unhappy", testCity);

        assertFalse(nbhd.equals(null));
        assertFalse(nbhd.equals(1234));

        // Different neighbourhood city
        assertFalse(nbhd.equals(new Neighbourhood("Fancy Slopes East Bank", testCity)));
        // Different neighbourhood name and city
        assertFalse(nbhd.equals(testNbhd));

        // City object
        assertFalse(nbhd.equals(testCity));
    }

    @Test
    public void testHashCode() {
        City testCity = new City("Vancouver", "BC", "CA");
        Neighbourhood testNbhd = new Neighbourhood("Fancy Slopes East Bank", testCity);
        assertEquals(nbhd.hashCode(), testNbhd.hashCode());
        assertEquals(nbhd.hashCode(), testCity.hashCode());
    }
}
