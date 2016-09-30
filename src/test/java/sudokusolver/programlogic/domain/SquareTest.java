/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import java.util.Set;
import java.util.TreeSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author kallionpetri
 */
public class SquareTest {
    private Square square;
    
    public SquareTest() {
    }
    
    @Before
    public void setUp() {
        this.square = new Square(new Location(1, 2), new Subgrid(new Location(2, 2), null), 1);
    }

    /**
     * Test of getNumber method, of class Square.
     */
    @Test
    public void testGetNumber() {
        assertEquals(square.getNumber(), 1);
    }

    /**
     * Test of getLocation method, of class Square.
     */
    @Test
    public void testGetLocation() {
        assertEquals(square.getLocation().getX(), 1);
        assertEquals(square.getLocation().getY(), 2);
    }

    /**
     * Test of getAbsoluteLocation method, of class Square.
     */
    @Test
    public void testGetAbsoluteLocation() {
        assertEquals(square.getAbsoluteLocation().getX(), 7);
        assertEquals(square.getAbsoluteLocation().getY(), 8);
    }

    /**
     * Test of setNumber method, of class Square.
     */
    @Test
    public void testSetNumber() {
        square.setNumber(3);
        assertEquals(square.getNumber(), 3);
    }

    /**
     * Test of getPossibilities method, of class Square.
     */
    @Test
    public void testGetPossibilitiesReturnsAnEmptySetFirst() {
        assertEquals(square.getPossibilities().size(), 0);
    }
    
    @Test
    public void testGetPossibilitiesReturnsTheSameSetThatWasSet() {
        Set<Integer> testSet = new TreeSet<>();
        testSet.add(1);
        testSet.add(2);
        testSet.add(3);
        square.setPossibilities(testSet);
        assertEquals(square.getPossibilities(), testSet);
        assertEquals(square.getPossibilities().size(), 3);
    }

    /**
     * Test of isSolved method, of class Square.
     */
    @Test
    public void testIsSolvedIfNumberNotMinusOne() {
        assertTrue(square.isSolved());
    }
    
    @Test
    public void testIsNotSolvedIfNumberIsMinusOne() {
        this.square.setNumber(-1);
        assertFalse(square.isSolved());
    }
    
}
