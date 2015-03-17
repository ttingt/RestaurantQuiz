package ca.ubc.cs.cpsc210.quiz.model.tests;

import ca.ubc.cs.cpsc210.quiz.model.Category;
import ca.ubc.cs.cpsc210.quiz.model.City;
import ca.ubc.cs.cpsc210.quiz.model.GeoArea;
import ca.ubc.cs.cpsc210.quiz.model.Restaurant;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RestaurantTest {

    Restaurant r;
    List<Category> categories;
    GeoArea geoArea;

    @Before
    public void setUp() {
        categories = new ArrayList<Category>();
        categories.add(new Category("Sushi", "Rolls"));
        geoArea = new City("Banana", "FR", "UI");
        r = new Restaurant("Rons", categories, "ID", "123 Birch", "A1B 2C3", geoArea);
    }

    @Test
    public void testGetAddress() {
        assertTrue(r.getAddress().equals("123 Birch"));
    }

    @Test
    public void testGetName() {
        assertTrue(r.getName().equals("Rons"));
    }

    @Test
    public void testGetCategories() {
        assertEquals(r.getCategories(), categories);
    }

    @Test
    public void testGetId() {
        assertTrue(r.getId().equals("ID"));
    }

    @Test
    public void testGetPostalCode() {
        assertTrue(r.getPostalCode().equals("A1B 2C3"));
    }

    @Test
    public void testGetGeographicalArea() {
        assertEquals(r.getGeographicalArea(), geoArea);
    }

    @Test
    public void testEquals() {
        Restaurant r2 = new Restaurant("Rons", categories, "ID", "123 Birch", "A1B 2C3", geoArea);

        assertTrue(r.equals(r));
        assertTrue(r.equals(r2));

        assertFalse(r.equals(null));
        assertFalse(r.equals(1234));
        // Different name
        assertFalse(r.equals(new Restaurant("Joeys", categories, "ID", "123 Birch", "A1B 2C3", geoArea)));
    }

    @Test
    public void testHashCode() {
        Restaurant r2 = new Restaurant("Rons", categories, "ID", "123 Birch", "A1B 2C3", geoArea);
        assertEquals(r.hashCode(), r2.hashCode());
    }

    @Test
    public void testToString() {
        assertTrue(r.toString().equals("Rons, Banana, FR, UI, A1B 2C3"));
    }
}