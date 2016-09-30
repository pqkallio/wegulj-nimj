/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author kallionpetri
 */
public class Grid implements Solvable {
    private final Map<Location, Subgrid> SUBGRIDS;
    private Map<Location, List<Integer>> numbers;
    
    public Grid() {
        this(null);
    }
    
    public Grid(int[] numbers) {
        this.numbers = createLocationsAndNumbersLists(numbers);
        this.SUBGRIDS = createSubgrids(this.numbers);
    }

    public List<Subgrid> getSubgrids() {
        return new ArrayList<>(SUBGRIDS.values());
    }
    
    public Set<Integer> getNumbersFromASubgridRow(int subgridY, int squareY) {
        Set<Integer> numberSet = new TreeSet<>();
        
        for (Subgrid subgrid : this.SUBGRIDS.values()) {
            if (subgrid.getLocation().getY() == subgridY) {
                numberSet.addAll(getNumbersFromASquareRow(subgrid, squareY));
            }
        }
        
        return numberSet;
    }
    
    private Set<Integer> getNumbersFromASquareRow(Subgrid subgrid, int squareY) {
        Set<Integer> numberSet = new TreeSet<>();
        
        for (Square square : subgrid.getSolvedSquares(true)) {
            if (square.getLocation().getY() == squareY) {
                numberSet.add(square.getNumber());
            }
        }
        
        return numberSet;
    }
    
    public Set<Integer> getNumbersFromASubgridCol(int subgridX, int squareX) {
        Set<Integer> numberSet = new TreeSet<>();
        
        for (Subgrid subgrid : this.SUBGRIDS.values()) {
            if (subgrid.getLocation().getX() == subgridX) {
                numberSet.addAll(getNumbersFromASquareCol(subgrid, squareX));
            }
        }
        
        return numberSet;
    }
    
    private Set<Integer> getNumbersFromASquareCol(Subgrid subgrid, int squareX) {
        Set<Integer> numberSet = new TreeSet<>();
        
        for (Square square : subgrid.getSolvedSquares(true)) {
            if (square.getLocation().getX() == squareX) {
                numberSet.add(square.getNumber());
            }
        }
        
        return numberSet;
    }

    private Map<Location, Subgrid> createSubgrids(Map<Location, List<Integer>> numberMap) {
        Map<Location, Subgrid> subgridMap = new TreeMap<>();
        
        for (Location loc : numberMap.keySet()) {
            subgridMap.put(loc, new Subgrid(loc, this, numberMap.get(loc)));
        }
        
        return subgridMap;
    }

    @Override
    public boolean isSolved() {
        for (Subgrid subgrid : SUBGRIDS.values()) {
            if (!subgrid.isSolved()) return false;
        }
        
        return true;
    }

    private Map<Location, List<Integer>> createLocationsAndNumbersLists(int[] numbers) {
        Map<Location, List<Integer>> numberMap = new TreeMap<>();
        int i = 9;
        int j = 0;
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Location loc = new Location(x, y);
                List<Integer> nums = new ArrayList<>();
                for (int k = j; k < i; k++) {
                    nums.add(numbers[k]);
                }
                
                numberMap.put(loc, nums);
                
                i += 9;
                j += 9;
            }
        }
        
        return numberMap;
    }

    public List<Square> getSquaresFromASubgridRow(int subgridY, int squareY) {
        List<Square> squaresFromARow = new ArrayList<>();
        for (Location loc : this.SUBGRIDS.keySet()) {
            if (loc.getY() == subgridY) {
                for (Location loc2 : this.SUBGRIDS.get(loc).getSquares().keySet()) {
                    if (loc2.getY() == squareY) {
                        squaresFromARow.add(this.SUBGRIDS.get(loc).getSquares().get(loc2));
                    }
                }
            }
        }
        
        Collections.sort(squaresFromARow);
        return squaresFromARow;
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (Square sq : getSquaresFromASubgridRow(i, j)) {
                    if (sq.getNumber() == -1) {
                        System.out.print("*");
                    } else {
                        System.out.print(sq.getNumber());
                    }
                }
                System.out.println();
            }
        }
    }

    public List<Subgrid> getSolvedSubgrids(boolean isSolved) {
        List<Subgrid> subgridList = new ArrayList<>();
        
        for (Subgrid subgrid : this.SUBGRIDS.values()) {
            if (subgrid.isSolved() == isSolved) {
                subgridList.add(subgrid);
            }
        }
        
        return subgridList;
    }

    public int[] getNumbers() {
        int[] currentNumbers = new int[81];
        int i = 0;
        for (Subgrid subgrid : SUBGRIDS.values()) {
            for (Square square : subgrid.getSquares().values()) {
                currentNumbers[i] = square.getNumber();
                i++;
            }
        }
        
        return currentNumbers;
    }
    
    public void setNumbers(int[] numbers) {
        int i = 9;
        int j = 0;
        List<List<Integer>> lists = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Location loc = new Location(x, y);
                List<Integer> nums = new ArrayList<>();
                for (int k = j; k < i; k++) {
                    nums.add(numbers[k]);
                }
                
                lists.add(nums);
                
                i += 9;
                j += 9;
            }
        }
        
        i = 0;
        
        for (Subgrid sg : this.SUBGRIDS.values()) {
            sg.setNumbers(lists.get(i));
            i++;
        }
    }
}
