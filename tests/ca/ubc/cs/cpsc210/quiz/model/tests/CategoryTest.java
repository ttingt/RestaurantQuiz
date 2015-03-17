package ca.ubc.cs.cpsc210.quiz.model.tests;

import ca.ubc.cs.cpsc210.quiz.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category c1;
    private Category c2;

    @Before
    public void setUp() {
        c1 = new Category("Gelato", "ice cream");
        c2 = new Category("Fish and Chips", "fish");
    }

    @Test
    public void testGetName() {
        assertTrue(c1.getName().equals("Gelato"));
        assertTrue(c2.getName().equals("Fish and Chips"));
    }

    @Test
    public void testGetAlias() {
        assertTrue(c1.getAlias().equals("ice cream"));
        assertTrue(c2.getAlias().equals("fish"));
    }

    @Test
    public void testEqualsTrue() {
        assertTrue(c1.equals(c1));
        assertTrue(c2.equals(c2));
        assertTrue(c1.equals(new Category("Gelato", "ice cream")));
        assertTrue(c2.equals(new Category("Fish and Chips", "fish")));

    }

    @Test
    public void testEqualsFalse() {
        assertFalse(c1.equals(c2));
        assertFalse(c2.equals(c1));
        // Not a category object
        assertFalse(c1.equals("abcdefg"));
        assertFalse(c1.equals(12345));
        // Name and alias are both different
        assertFalse(c1.equals(new Category("Crepes", "flat pancakes")));
        // Name is the same but alias is different
        assertFalse(c1.equals(new Category("Gelato", "dude no")));
        // Name is different but alias is the same
        assertFalse(c1.equals(new Category("Gel", "ice cream")));
    }

    @Test
    public void testToString() {
        assertTrue(c1.toString().equals(c1.getName()));
        assertTrue(c2.toString().equals(c2.getName()));
    }
}