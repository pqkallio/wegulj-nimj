/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.controllers;

import sudokusolver.programlogic.domain.Grid;
import java.util.Scanner;
import sudokusolver.programlogic.domain.Square;

/**
 *
 * @author kallionpetri
 */
public class SolvingController {
    private Grid grid;
    
    public SolvingController(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }
    
    public boolean grind() {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        while (!this.grid.isSolved()) {
//            System.out.println("Round " + i);
            GridSolvingController gsc = new GridSolvingController(this.grid);
            gsc.grind();
//            grid.print();
//            scanner.nextLine();
            i++;
            if (!gsc.progress()) {
                if (gsc.getSquareWithLeastPossibilities().getPossibilities().isEmpty()) {
//                    System.out.println("No more possibilities, backing up...");
                    return false;
                } else {
//                    System.out.println("*********************");
//                    System.out.println("CREATING NEW SCENARIO");
//                    System.out.println("*********************");
//                    System.out.print("Square with least possibilities: ");
//                    System.out.println(gsc.getSquareWithLeastPossibilities().getAbsoluteLocation());
//                    System.out.print("Possibilities: ");
//                    System.out.println(gsc.getSquareWithLeastPossibilities().getPossibilities());
                    return newScenario(gsc.getSquareWithLeastPossibilities());
                }
            }
        }
        
        return true;
    }

    private boolean newScenario(Square square) {
        int[] gridSaveState = grid.getNumbers();
        for (Integer number : square.getPossibilities()) {
//            System.out.println("Trying: " + number + " in " + square.getAbsoluteLocation());
            square.setNumber(number);
            SolvingController sc = new SolvingController(grid);
            if (sc.grind()) return true;
//            System.out.println("************************");
//            System.out.println("Trying something else...");
//            System.out.println("************************");
            grid.setNumbers(gridSaveState);
        }
        
        return false;
    }
}
