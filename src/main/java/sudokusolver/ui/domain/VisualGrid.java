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
import sudokusolver.programlogic.domain.Grid;
import sudokusolver.programlogic.domain.Subgrid;

/**
 *
 * @author kallionpetri
 */
public class VisualGrid extends JPanel {
    private final List<VisualSubgrid> VISUAL_SUBGRIDS;

    public VisualGrid(Grid grid) {
        this.VISUAL_SUBGRIDS = createSubgrids(grid.getSubgrids());
    }

    private List<VisualSubgrid> createSubgrids(List<Subgrid> subgrids) {
        List<VisualSubgrid> visualSubgrids = new ArrayList<>();
        
        for (Subgrid subgrid : subgrids) {
            visualSubgrids.add(new VisualSubgrid(subgrid));
        }
        
        return visualSubgrids;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
    }
}