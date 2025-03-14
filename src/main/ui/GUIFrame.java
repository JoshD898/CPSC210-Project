package ui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Drawing;
import model.Gallery;

/*
 * The main frame from which the gallery GUI is shown
 */
public class GUIFrame extends JFrame {
    private Gallery gallery;
    private Drawing selectedDrawing;
    private File selectedFile;

    private GalleryPanel gp;
    
    /*
     * EFFECTS: Sets up the window in which the gallery will be shown
     */
    public GUIFrame() {

    }

    /*
     * EFFECTS: Constructs a panel with 3 buttons: Add, Edit and Delete
     */
    private JPanel editButtonPanel() {
        return null;
    }

    /*
     * EFFECTS: Constructs a panel with 1 button: Manage saves
     */
    private JPanel manageSavePanel() {
        return null;
    }
}


