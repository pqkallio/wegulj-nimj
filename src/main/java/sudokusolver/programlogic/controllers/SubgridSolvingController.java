/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.controllers;

import java.util.Collections;
import sudokusolver.utils.*;
import java.util.Set;
import java.util.TreeSet;
import sudokusolver.programlogic.domain.Grid;
import sudokusolver.programlogic.domain.Square;
import sudokusolver.programlogic.domain.Subgrid;

/**
 *
 * @author kallionpetri
 */
public class SubgridSolvingController {
    private Set<Integer> possibleNumbers;
    private final Grid GRID;
    private final Subgrid SUBGRID;
    private Square squareWithLeastPossibilities;
    
    public SubgridSolvingController(Grid grid, Subgrid subgrid) {
        this.squareWithLeastPossibilities = subgrid.getSolvedSquares(false).get(0);
        this.GRID = grid;
        this.SUBGRID = subgrid;
        this.possibleNumbers = new TreeSet<>();
        Collections.addAll(this.possibleNumbers, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        this.possibleNumbers = SetOperations.relativeComplement(this.possibleNumbers, subgrid.getSolvedNumbers());
    }

    public boolean grind() {
        for (Square square : this.SUBGRID.getSolvedSquares(false)) {
//            System.out.println("SQUARE @ " + square.getAbsoluteLocation().toString());
            Set<Integer> unsolved = new TreeSet<>(this.possibleNumbers);
//            System.out.println("Possible numbers in the square: " + unsolved);
            unsolved = SetOperations.relativeComplement(unsolved, this.GRID.getNumbersFromASubgridRow(this.SUBGRID.getLocation().getY(), square.getLocation().getY()));
//            System.out.println("Possible numbers without found in the same row: " + unsolved);
            unsolved = SetOperations.relativeComplement(unsolved, this.GRID.getNumbersFromASubgridCol(this.SUBGRID.getLocation().getX(), square.getLocation().getX()));
//            System.out.println("Possible numbers without found in the same col: " + unsolved);
            
            if (unsolved.size() == 1) {
                square.setNumber((Integer)unsolved.toArray()[0]);
                return true;
            }
            
            square.setPossibilities(unsolved);
            checkIfHasLeastPossibilities(square);
        }
        
        return false;
    }

    private void checkIfHasLeastPossibilities(Square square) {
        if (square.getPossibilities().size() 
                < this.squareWithLeastPossibilities.getPossibilities().size()) {
            this.squareWithLeastPossibilities = square;
        }
    }

    public Square getSquareWithLeastPossibilities() {
        return squareWithLeastPossibilities;
    }
}
