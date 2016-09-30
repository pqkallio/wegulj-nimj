/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.controllers;

import sudokusolver.programlogic.domain.Grid;
import sudokusolver.programlogic.domain.Square;
import sudokusolver.programlogic.domain.Subgrid;

/**
 *
 * @author kallionpetri
 */
public class GridSolvingController {
    private final Grid GRID;
    private boolean progress;
    private Square squareWithLeastPossibilities;
    
    public GridSolvingController(Grid grid) {
        this.GRID = grid;
        this.squareWithLeastPossibilities = null;
        this.progress = false;
    }
    
    public boolean grind() {
        for (int i = 0; i < this.GRID.getSubgrids().size(); i++) {
            Subgrid sg = this.GRID.getSubgrids().get(i);
            
            if (!sg.isSolved()) {
                SubgridSolvingController sgsc = new SubgridSolvingController(this.GRID, sg);
                if (sgsc.grind()) {
                    this.progress = true;
                    i--;
                }
                
                if (!sg.isSolved()) {
                    if (squareWithLeastPossibilities == null 
                            || sgsc.getSquareWithLeastPossibilities().getPossibilities().size() 
                                < squareWithLeastPossibilities.getPossibilities().size()) {
                        squareWithLeastPossibilities = sgsc.getSquareWithLeastPossibilities();
                    }
                }
            }
        }
        
        return this.GRID.isSolved();
    }

    boolean progress() {
        return this.progress;
    }

    public Square getSquareWithLeastPossibilities() {
        return squareWithLeastPossibilities;
    }
}
