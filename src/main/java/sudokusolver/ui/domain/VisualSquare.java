
package sudokusolver.ui.domain;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sudokusolver.programlogic.domain.Square;

public class VisualSquare extends JPanel implements Comparable<VisualSquare> {
    private final Square SUDOKU_SQUARE;
    private final JTextField CONTENT;
    
    public VisualSquare(Square sudokuSquare) {
        this.SUDOKU_SQUARE = sudokuSquare;
        this.CONTENT = new JTextField(1);
        this.CONTENT.setBorder(null);
        
        if (this.SUDOKU_SQUARE.getNumber() != -1) {
            this.CONTENT.setText("" + this.SUDOKU_SQUARE.getNumber());
            this.CONTENT.setEnabled(false);
        }
        
        this.add(this.CONTENT);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2d);
    }

    @Override
    public int compareTo(VisualSquare another) {
        return this.SUDOKU_SQUARE.getLocation().compareTo(another.SUDOKU_SQUARE.getLocation());
    }
}
