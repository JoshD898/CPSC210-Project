package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;

import javax.swing.JPanel;

import model.Drawing;

/*
 * A panel that displays a drawing and its title
 */
public class DrawingPanel extends JPanel  {   
    private Color color;
    private int width;
    private int height;
    private String title;

    /*
     * EFFECTS: Sets the size and background color of the panel.
     */
    public DrawingPanel(Drawing d, UserInterface guiFrame) {
        this.color = d.getColor();
        this.width = d.getWidth();
        this.height = d.getHeight();
        this.title = d.getTitle();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guiFrame.setSelectedDrawing(d);
            }
        });

        if (guiFrame.getSelectedDrawing() != null && title == guiFrame.getSelectedDrawing().getTitle()) {
            setBackground(Color.GRAY);
        } else {
            setBackground(Color.WHITE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawDrawing(g);
    }

    /*
     * EFFECTS: Draws the square and title on the panel.
     */
    private void drawDrawing(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int squareX = (panelWidth - width) / 2;
        int squareY = (panelHeight - height) / 2 - 20; 
    
        g.setColor(color);     
        g.fillRect(squareX, squareY, width, height);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics metrics = g.getFontMetrics();
        int textX = (panelWidth - metrics.stringWidth(title)) / 2;
        int textY = squareY + height + metrics.getHeight();
        g.drawString(title, textX, textY);
    }
}