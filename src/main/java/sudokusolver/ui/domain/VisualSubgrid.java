/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver.ui.domain;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import sudokusolver.programlogic.domain.Square;
import sudokusolver.programlogic.domain.Subgrid;

/**
 *
 * @author kallionpetri
 */
public class VisualSubgrid extends JPanel implements Comparable<VisualSubgrid> {
    private final Subgrid SUBGRID;
    private final List<VisualSquare> VISUAL_SQUARES;
    
    public VisualSubgrid(Subgrid subgrid) {
        this.SUBGRID = subgrid;
        this.VISUAL_SQUARES = createVisualSquares((ArrayList<Square>)subgrid.getSquares().values());
    }

    private List<VisualSquare> createVisualSquares(List<Square> squares) {
        List<VisualSquare> visualSquares = new ArrayList<>();
        
        for (Square square : squares) {
            visualSquares.add(new VisualSquare(square));
        }
        
        return visualSquares;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
    }

    @Override
    public int compareTo(VisualSubgrid another) {
        return this.SUBGRID.getLocation().compareTo(another.SUBGRID.getLocation());
    }
}
