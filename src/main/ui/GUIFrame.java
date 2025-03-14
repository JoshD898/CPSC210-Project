package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Drawing;
import model.Gallery;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * The main frame from which the gallery GUI is shown
 */
public class GUIFrame extends JFrame {
    private static int MENU_HEIGHT = 50;
    private int MAX_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    private Gallery gallery;
    private Drawing selectedDrawing;

    private JScrollPane galleryPane;
    
    /*
     * EFFECTS: Sets up the window in which the gallery will be shown
     */
    public GUIFrame() {
        super("My Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);

        gallery = new Gallery();

        galleryPane = new JScrollPane(new GalleryPanel(gallery, this));

        add(galleryPane);
        add(editButtonPanel(), BorderLayout.SOUTH);
        add(savePanel(), BorderLayout.NORTH);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*
     * EFFECTS: Constructs a panel with 3 buttons: Add, Edit and Delete
     */
    private JPanel editButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 0, 0));
        panel.setPreferredSize(new Dimension(0, MENU_HEIGHT));
        panel.setMaximumSize(new Dimension(MAX_WIDTH, MENU_HEIGHT));

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(e -> handleDeleteButton());

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);

        return panel;
    }

    /*
     * EFFECTS: Constructs a panel with 2 buttons: Save and Load
     */
    private JPanel savePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 0, 0));
        panel.setPreferredSize(new Dimension(0, MENU_HEIGHT));
        panel.setMaximumSize(new Dimension(MAX_WIDTH, MENU_HEIGHT));

        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        saveButton.addActionListener(e -> handleSaveButton());
        loadButton.addActionListener(e -> handleLoadButton());

        panel.add(saveButton);
        panel.add(loadButton);

        return panel;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sets the current gallery to that saved under the file "./data/save.json", then re-renders the gallery panel
     */
    private void handleLoadButton() {
        try {
            JsonReader reader = new JsonReader("./data/save.json");
            gallery = reader.readGallery();
            selectedDrawing = gallery.getDrawing(reader.readSelectedDrawingTitle());
            galleryPane.setViewportView(new GalleryPanel(gallery, this)); 
        } catch (IOException e) {
            // do nothing
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Saves the current gallery to "./data/save.json"
     */
    private void handleSaveButton() {
        try {
            JsonWriter writer = new JsonWriter("./data/save.json");
            writer.open();
            writer.write(gallery, null);
            writer.close();
        } catch (IOException e) {
            // do nothing
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Removes selectedDrawing from the gallery
     */
    private void handleDeleteButton() {
        gallery.removeDrawing(selectedDrawing);
        galleryPane.setViewportView(new GalleryPanel(gallery, this));
    }

    public Drawing getSelectedDrawing() {
        return selectedDrawing;
    }

    public void setSelectedDrawing(Drawing d) {
        selectedDrawing = d;
        galleryPane.setViewportView(new GalleryPanel(gallery, this));
    }

    public void addDrawing(Drawing d) {
        gallery.addDrawing(d);
    }
}


