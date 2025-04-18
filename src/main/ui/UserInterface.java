package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Drawing;
import model.EventLog;
import model.Gallery;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * The main frame from which the gallery GUI is shown
 */
public class UserInterface extends JFrame {
    private static final int MENU_HEIGHT = 50;
    private static final int MAX_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    private Gallery gallery;
    private Drawing selectedDrawing;

    private JScrollPane galleryPane;

    /*
     * EFFECTS: Sets up the window in which the gallery will be shown
     */
    public UserInterface() {
        super("My Gallery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 1024);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                printLogs();
                dispose();
            }
        });

        setVisible(true);
        showLoadingScreen();

        gallery = new Gallery();
        galleryPane = new JScrollPane(new GalleryPanel(gallery, this));

        add(galleryPane);
        add(editButtonPanel(), BorderLayout.SOUTH);
        add(savePanel(), BorderLayout.NORTH);
        revalidate();

        
    }

    /*
     * EFFECTS: Displays the loading screen image for 5 seconds, then clears the
     * frame
     */
    private void showLoadingScreen() {
        add(new JLabel(new ImageIcon("res/LoadingScreen.png")));
        revalidate();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Do nothing
        }

        getContentPane().removeAll();
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

        addButton.addActionListener(e -> switchToEditPanel(true));
        editButton.addActionListener(e -> switchToEditPanel(false));
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
     * EFFECTS: Sets the current gallery to that saved under the file
     * "./data/save.json",
     * then re-renders the gallery panel
     */
    private void handleLoadButton() {
        try {
            JsonReader reader = new JsonReader("./data/save.json");
            gallery = reader.readGallery();
            if (reader.readSelectedDrawingTitle() != null) {
                selectedDrawing = gallery.getDrawing(reader.readSelectedDrawingTitle());
            }
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
            writer.write(gallery, selectedDrawing);
            writer.close();
        } catch (IOException e) {
            // do nothing
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Removes selectedDrawing from the gallery, sets selectedDrawing to
     * null.
     */
    private void handleDeleteButton() {
        gallery.removeDrawing(selectedDrawing);
        galleryPane.setViewportView(new GalleryPanel(gallery, this));
        selectedDrawing = null;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Switches to the edit menu to add or edit a drawing
     */
    private void switchToEditPanel(Boolean isNewDrawing) {
        if (isNewDrawing || selectedDrawing != null) {
            getContentPane().removeAll();
            add(new EditPanel(this, isNewDrawing));
            revalidate();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Reset the frame to show the gallery panel and buttons
     */
    public void switchToGalleryPanel() {
        getContentPane().removeAll();
        galleryPane.setViewportView(new GalleryPanel(gallery, this));
        add(galleryPane);
        add(editButtonPanel(), BorderLayout.SOUTH);
        add(savePanel(), BorderLayout.NORTH);
        revalidate();
    }

    /*
     * EFFECTS: Returns selectedDrawing
     */
    public Drawing getSelectedDrawing() {
        return selectedDrawing;
    }

    /*
     * EFFECTS: Sets selectedDrawing
     */
    public void setSelectedDrawing(Drawing d) {
        selectedDrawing = d;
        galleryPane.setViewportView(new GalleryPanel(gallery, this));
    }

    /*
     * EFFECTS: Adds a drawing to the gallery
     */
    public void addDrawing(Drawing d) {
        gallery.addDrawing(d);
    }

    /*
     * EFFECTS: Prints every event log
     */
    private void printLogs() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDescription() + "     |     " + e.getDate());
        }
    }
}
