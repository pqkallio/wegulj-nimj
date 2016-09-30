/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.programlogic.domain;

/**
 *
 * @author kallionpetri
 */
public class Location implements Comparable<Location> {
    private final int X;
    private final int Y;
    
    public Location(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        
        Location comparedTo = (Location)obj;
        
        return this.X == comparedTo.getX() && this.Y == comparedTo.getY();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.X;
        hash = 97 * hash + this.Y;
        return hash;
    }

    @Override
    public int compareTo(Location o) {
        if (this.Y < o.Y) {
            return -1;
        } else if (this.Y == o.Y) {
            if (this.X < o.X) return -1;
            else if (this.X > o.X) return 1;
            else return 0;
        } 
        else return 1;
    }
    
    @Override
    public String toString() {
        return "[" + this.X + ", " + this.Y + "]";
    }
}
