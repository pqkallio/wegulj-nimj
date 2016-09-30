/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author kallionpetri
 */
public class LocationTest {
    private Location loc;
    
    public LocationTest() {
    }
    
    @Before
    public void setUp() {
        this.loc = new Location(0, 1);
    }

    /**
     * Test of getX method, of class Location.
     */
    @Test
    public void testGetX() {
        assertEquals(0, loc.getX());
    }

    /**
     * Test of getY method, of class Location.
     */
    @Test
    public void testGetY() {
        assertEquals(1, loc.getY());
    }

    /**
     * Test of equals method, of class Location.
     */
    @Test
    public void equalsIfAnotherLocationHasSameXAndY() {
        Location loc2 = new Location(0, 1);
        assertEquals(loc2, loc);
    }
    
    @Test
    public void notEqualsIfClassDoesntMatch() {
        Locazione loc2 = new Locazione(0, 1);
        assertNotEquals(loc2, loc);
    }
    
    @Test
    public void notEqualsIfAnotherLocationHasDifferentX() {
        Location loc2 = new Location(1, 1);
        assertNotEquals(loc2, loc);
    }
    
    @Test
    public void notEqualsIfAnotherLocationHasDifferentY() {
        Location loc2 = new Location(0, 0);
        assertNotEquals(loc2, loc);
    }

    /**
     * Test of hashCode method, of class Location.
     */
    @Test
    public void twoLocationsThatEqualHaveTheSameHashcode() {
        Location loc2 = new Location(0, 1);
        assertEquals(loc2.hashCode(), loc.hashCode());
    }
    
    @Test
    public void twoLocationsThatDontEqualHaveDifferentHashcodes() {
        Location loc2 = new Location(1, 1);
        assertNotEquals(loc2.hashCode(), loc.hashCode());
    }

    /**
     * Test of compareTo method, of class Location.
     */
    @Test
    public void compareToReturnsMinusOneIfComparablesYIsSmaller() {
        Location loc2 = new Location(0, 0);
        assertEquals(loc2.compareTo(loc), -1);
    }
    
    @Test
    public void compareToReturnsOneIfComparablesYIsGreater() {
        Location loc2 = new Location(0, 2);
        assertEquals(loc2.compareTo(loc), 1);
    }
    
    @Test
    public void compareToReturnsMinusOneIfComparablesYIsLess() {
        Location loc2 = new Location(0, 0);
        assertEquals(loc2.compareTo(loc), -1);
    }
    
    @Test
    public void compareToReturnsMinusOneIfComparablesYIsTheSameAndXIsSmaller() {
        Location loc2 = new Location(0, 0);
        Location loc3 = new Location(1, 0);
        assertEquals(loc2.compareTo(loc3), -1);
    }
    
    @Test
    public void compareToReturnsZeroIfComparablesYAndXAreTheSame() {
        Location loc2 = new Location(0, 1);
        assertEquals(loc2.compareTo(loc), 0);
    }
    
    @Test
    public void compareToReturnsOneIfComparablesYIsTheSameAndXIsGreater() {
        Location loc2 = new Location(1, 1);
        assertEquals(loc2.compareTo(loc), 1);
    }
    
    public class Locazione {
        private final int X;
        private final int Y;
        
        public Locazione(int x, int y) {
            this.X = x;
            this.Y = y;
        }

        public int getX() {
            return X;
        }

        public int getY() {
            return Y;
        }
    }
}
