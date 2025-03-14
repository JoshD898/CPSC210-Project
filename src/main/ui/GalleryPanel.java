package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Drawing;
import model.Gallery;

/*
 * A panel of vertically stacked DrawingPanels
 */
public class GalleryPanel extends JPanel {    
    /*
     * EFFECTS: Creates a new panel that is a vertical stack of DrawingPanels for every drawing in gallery
     */
    private final int MAX_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    public GalleryPanel(Gallery gallery) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        List<Drawing> drawings = gallery.getDrawingList();

        for (Drawing drawing : drawings) {
            int panelHeight = drawing.getHeight() + 100;

            DrawingPanel drawingPanel = new DrawingPanel(drawing, false);
            drawingPanel.setPreferredSize(new Dimension(0, panelHeight));
            drawingPanel.setMaximumSize(new Dimension(MAX_WIDTH, panelHeight));
            add(drawingPanel);
        }
    }
}
