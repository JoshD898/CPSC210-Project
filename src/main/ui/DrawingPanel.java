package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Drawing;

/*
 * A panel that displays a drawing and it's title
 */
public class DrawingPanel extends JPanel{
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 300;
    
    private Color color;
    private int width;
    private int height;
    private String title;

    /*
     * EFFECTS: sets the size and background color of the panel. Renders the drawing above the title.
     */
    public DrawingPanel(Drawing d) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDrawing(g);
    }

    /*
     * EFFECTS: Draws the square and its title on the panel.
     */
    private void drawDrawing(Graphics g) {

    }
}