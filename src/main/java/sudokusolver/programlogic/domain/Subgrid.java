/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author kallionpetri
 */
public class Subgrid implements Solvable {
    private final Location LOCATION;
    private final Map<Location, Square> SQUARES;
    private final Grid GRID;
    
    public Subgrid(Location location, Grid grid) {
        this(location, grid, null);
    }
    
    public Subgrid(Location location, Grid grid, List<Integer> numbers) {
        this.LOCATION = location;
        this.GRID = grid;
        this.SQUARES = createSquares(numbers);
//        System.out.println("Subgrid " + this.LOCATION + " squares:");
//        for (Location loc : this.SQUARES.keySet()) {
//            Square sq = this.SQUARES.get(loc);
//            System.out.println("  " + loc + " / " + sq.getAbsoluteLocation() + ": " + sq.getNumber());
//        }
//        System.out.println("**********************");
    }

    public Location getLocation() {
        return LOCATION;
    }

    public Grid getGrid() {
        return GRID;
    }

    public Map<Location, Square> getSquares() {
        return SQUARES;
    }
    
    public Square getSquare(int x, int y) {
        return this.SQUARES.get(new Location(x, y));
    }
    
    public void setNumbers(List<Integer> numbers) {
        int i = 0;
        
        for (Square square : this.SQUARES.values()) {
            square.setNumber(numbers.get(i));
            i++;
        }
    }
    
    @Override
    public boolean isSolved() {
        return getSolvedSquares(true).size() == 9;
    }
    
    public List<Square> getSolvedSquares(boolean isSolved) {
        List<Square> squareList = new ArrayList<>();
        
        for (Square square : this.SQUARES.values()) {
            if (square.isSolved() == isSolved) {
                squareList.add(square);
            }
        }
        
        return squareList;
    }
    
    public Set<Integer> getSolvedNumbers() {
        Set<Integer> solvedNumbers = new TreeSet<>();
        
        for (Square square : this.SQUARES.values()) {
            if (square.getNumber() != -1) {
                solvedNumbers.add(square.getNumber());
            }
        }
        
        return solvedNumbers;
    }

    private Map<Location, Square> createSquares(List<Integer> numbers) {
        Map<Location, Square> squareMap = new TreeMap<>();
        int i = 0;
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Location loc = new Location(x, y);
                
                if (numbers != null) {
                    squareMap.put(loc, new Square(loc, this, numbers.get(i)));
                    i++;
                } else {
                    squareMap.put(loc, new Square(loc, this, -1));
                }
                
            }
        }
        
        return squareMap;
    }
}
