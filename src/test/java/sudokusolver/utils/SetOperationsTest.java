/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.utils;

import java.util.Collections;
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
public class SetOperationsTest {
    public Set<Integer> possibleNumbers;
    
    public SetOperationsTest() {
    }
    
    @Before
    public void setUp() {
        this.possibleNumbers = new TreeSet<>();
        Collections.addAll(this.possibleNumbers, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    /**
     * Test of relativeComplement method, of class SetOperations.
     */
    @Test
    public void testRelativeComplement() {
        System.out.println(this.possibleNumbers);
        Set<Integer> testSet = new TreeSet<>();
        Collections.addAll(testSet, 2, 4, 6, 8);
        this.possibleNumbers = SetOperations.relativeComplement(this.possibleNumbers, testSet);
        assertFalse(this.possibleNumbers.contains(2));
        assertFalse(this.possibleNumbers.contains(4));
        assertTrue(this.possibleNumbers.contains(1));
        assertTrue(this.possibleNumbers.contains(3));
        System.out.println(this.possibleNumbers);
    }
    
}
