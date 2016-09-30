/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.utils;

import java.util.Set;

/**
 *
 * @author kallionpetri
 */
public class SetOperations {
    
    public static Set<Integer> relativeComplement(Set a, Set b) {
        for (Object x : b) {
            if (a.contains(x)) a.remove(x);
        }
        
        return a;
    }
}
