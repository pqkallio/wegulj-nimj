/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author kallionpetri
 */
public class Square implements Solvable, Comparable<Square> {
    private final Location LOCATION;
    private final Subgrid SUBGRID;
    private int number;
    private Set<Integer> possibilities;
    
    public Square(Location location, Subgrid subgrid, int number) {
        this.LOCATION = location;
        this.SUBGRID = subgrid;
        this.number = number;
        this.possibilities = new TreeSet<>();
    }

    public int getNumber() {
        return number;
    }

    public Location getLocation() {
        return LOCATION;
    }
    
    public Location getAbsoluteLocation() {
        int x = this.SUBGRID.getLocation().getX() * 3 + this.LOCATION.getX();
        int y = this.SUBGRID.getLocation().getY() * 3 + this.LOCATION.getY();
        
        return new Location(x, y);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Integer> getPossibilities() {
        return possibilities;
    }
    
    public void setPossibilities(Set<Integer> possibilities) {
        this.possibilities = new TreeSet<>(possibilities);
    }

    @Override
    public boolean isSolved() {
        return this.number != -1;
    }

    @Override
    public int compareTo(Square o) {
        return this.getAbsoluteLocation().compareTo(o.getAbsoluteLocation());
    }
}
