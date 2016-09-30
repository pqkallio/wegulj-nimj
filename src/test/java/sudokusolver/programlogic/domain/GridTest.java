/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author kallionpetri
 */
public class GridTest {
    private Grid grid;
    private int[] numbers;
    
    public GridTest() {
    }
    
    @Before
    public void setUp() {
        /*
        
        ***|58*|3**
        35*|2*6|789
        *27|1**|5**
        -----------
        ***|*9*|***
        ***|***|***
        ***|***|267
        -----------
        **6|*7*|***
        91*|36*|8*5
        273|9**|*1*
        
        */
        int[] nums =    {-1, -1, -1,  3,  5, -1, -1,  2,  7,
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
    }

    /**
     * Test of getSubgrids method, of class Grid.
     */
    @Test
    public void testGetSubgrids() {
        assertEquals(grid.getSubgrids().get(0).getClass(), Subgrid.class);
    }
    
    @Test
    public void testGetSubgridsPt2() {
        assertEquals(grid.getSubgrids().size(), 9);
    }

    /**
     * Test of getNumbersFromASubgridRow method, of class Grid.
     */
    @Test
    public void testGetNumbersFromASubgridRow() {
        int[] test = {3, 5, 8};
        Set<Integer> rowNumbers = grid.getNumbersFromASubgridRow(0, 0);
        
        int i = 0;
        for (Integer number : rowNumbers) {
            assertEquals((int)number, test[i]);
            i++;
        }
    }
    
    @Test
    public void testGetNumbersFromASubgridRowPt2() {
        Set<Integer> rowNumbers = grid.getNumbersFromASubgridRow(1, 1);
        
        assertEquals(rowNumbers.size(), 0);
    }

    /**
     * Test of getNumbersFromASubgridCol method, of class Grid.
     */
    @Test
    public void testGetNumbersFromASubgridCol() {
        int[] test = {2, 3, 9};
        Set<Integer> rowNumbers = grid.getNumbersFromASubgridCol(0, 0);
        
        int i = 0;
        for (Integer number : rowNumbers) {
            assertEquals((int)number, test[i]);
            i++;
        }
    }
    
    @Test
    public void testGetNumbersFromASubgridColPt2() {
        int[] test = {5, 7, 9};
        Set<Integer> rowNumbers = grid.getNumbersFromASubgridCol(2, 2);
        
        int i = 0;
        for (Integer number : rowNumbers) {
            assertEquals((int)number, test[i]);
            i++;
        }
    }

    /**
     * Test of isSolved method, of class Grid.
     */
    @Test
    public void testIsSolved() {
        assertFalse(grid.isSolved());
    }
    
    @Test
    public void testIsSolvedPt2() {
        for (Subgrid sg : grid.getSubgrids()) {
            for (Square sq : sg.getSolvedSquares(false)) {
                sq.setNumber(1);
            }
        }
        
        assertTrue(grid.isSolved());
    }
}
