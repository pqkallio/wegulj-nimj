/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author kallionpetri
 */
public class SubgridTest {
    private Subgrid subgrid;
    private Grid grid;
    private int[] numbers;
    
    public SubgridTest() {
    }
    
    @Before
    public void setUp() {
        int[] nums = {-1, -1, -1,  3,  5, -1, -1,  2,  7,
                          5,  8, -1,  2, -1,  6,  1, -1, -1,
                          3, -1, -1,  7,  8,  9,  5, -1, -1,
                         -1, -1, -1, -1, -1, -1, -1, -1, -1,
                         -1,  9, -1, -1, -1, -1, -1, -1, -1,
                         -1, -1, -1, -1, -1, -1,  2,  6,  7,
                         -1, -1,  6,  9,  1, -1,  2,  7,  3,
                         -1,  7, -1,  3,  6, -1,  9, -1, -1,
                         -1, -1, -1,  8, -1,  5, -1,  1, -1};
        this.numbers = nums;
        
        this.grid = new Grid(numbers);
        
        this.subgrid = grid.getSubgrids().get(0);
    }

    /**
     * Test of getLocation method, of class Subgrid.
     */
    @Test
    public void testGetLocation() {
        Location loc = new Location(0, 0);
        assertEquals(this.subgrid.getLocation(), loc);
    }

    /**
     * Test of getBoard method, of class Subgrid.
     */
    @Test
    public void testGetGrid() {
        assertEquals(this.grid, subgrid.getGrid());
    }

    /**
     * Test of getSquares method, of class Subgrid.
     */
    @Test
    public void testGetSquares() {
        assertEquals(this.subgrid.getSquares().size(), 9);
    }

    /**
     * Test of getSquare method, of class Subgrid.
     */
    @Test
    public void testGetSquare() {
        assertEquals(subgrid.getSquare(0, 0).getClass(), Square.class);
    }
    
    @Test
    public void testGetSquarePt2() {
        assertEquals(subgrid.getSquare(2, 2).getNumber(), 7);
    }

    /**
     * Test of isSolved method, of class Subgrid.
     */
    @Test
    public void testIsNotSolvedIfAllSquaresDontHaveNumbers() {
        assertFalse(subgrid.isSolved());
    }
    
    @Test
    public void testIsSolvedIfAllSquaresHaveNumbers() {
        int i = 1;
        
        for (Square square : this.subgrid.getSquares().values()) {
            square.setNumber(i);
            i++;
        }
        
        assertTrue(subgrid.isSolved());
    }

    /**
     * Test of getSolvedSquares method, of class Subgrid.
     */
    @Test
    public void testGetSolvedSquares() {
        assertEquals(subgrid.getSolvedSquares(true).size(), 4);
    }

    /**
     * Test of getSolvedNumbers method, of class Subgrid.
     */
    @Test
    public void testGetSolvedNumbers() {
        assertEquals(subgrid.getSolvedNumbers().size(), 4);
    }
    
    @Test
    public void testGetSolvedNumbersPt2() {
        int[] testSolved = {2, 3, 5, 7};
        List<Integer> solvedNumbers = new ArrayList<>();
        for (Integer number : subgrid.getSolvedNumbers()) {
            solvedNumbers.add(number);
        }
        
        for (int i = 0; i < solvedNumbers.size(); i++) {
            assertEquals((int)solvedNumbers.get(i), testSolved[i]);
        }
    }
}
