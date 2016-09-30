/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.controllers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import sudokusolver.programlogic.domain.Grid;

/**
 *
 * @author kallionpetri
 */
public class SubgridSolvingControllerTest {
    private SubgridSolvingController sgc1;
    private SubgridSolvingController sgc2;
    private Grid grid;
    
    public SubgridSolvingControllerTest() {
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
        this.grid = new Grid(nums);
        this.sgc1 = new SubgridSolvingController(grid, grid.getSubgrids().get(1));
        this.sgc2 = new SubgridSolvingController(grid, grid.getSubgrids().get(0));
    }

    /**
     * Test of grind method, of class SubgridSolvingController.
     */
    @Test
    public void testGrindTruePt1() {
        assertTrue(this.sgc1.grind());
    }
    
    @Test
    public void testGrindTruePt2() {
        this.sgc1.grind();
        assertEquals(grid.getSubgrids().get(1).getSquare(1, 1).getNumber(), 4);
    }
    
    @Test
    public void testGrindTruePt3() {
        System.out.println("Before:");
        this.grid.print();
        this.sgc1.grind();
        this.sgc1.grind();
        System.out.println("-------");
        System.out.println("After:");
        this.grid.print();
        assertEquals(grid.getSubgrids().get(1).getSquare(1, 2).getNumber(), 3);
    }
    
    @Test
    public void testGrindFalsePt1() {
        assertFalse(this.sgc2.grind());
    }
}
