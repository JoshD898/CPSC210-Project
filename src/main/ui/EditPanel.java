package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.Drawing;

/*
 * A panel that allows users to specify the characteristics of a drawing (height, width, title and color)
 */
public class EditPanel extends JPanel {
    private JTextField titleField;
    private JSpinner widthSpinner, heightSpinner;
    private JSlider redSlider, greenSlider, blueSlider;
    private JButton applyButton;

    private GUIFrame guiFrame;
    private Drawing selectedDrawing;

    /*
     * REQUIRES: If isNewDrawing is false, the guiFrame must have an active selected drawing.
     * EFFECTS: Creates a new panel that allows for user to modify characteristics of a selected drawing, or set the characteristics of a new drawing
     */
    public EditPanel(GUIFrame guiFrame, Boolean isNewDrawing) {
        this.guiFrame = guiFrame;
        
        init();

        if (!isNewDrawing) {
            selectedDrawing = guiFrame.getSelectedDrawing();
            loadFields();
        }

        applyButton.addActionListener(e -> applyChanges(isNewDrawing));        
    }

    /*
     * EFFECTS: Initialize the input fields
     */
    private void init() {
        setLayout(new GridLayout(7, 2, 5, 5)); 
        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Width:"));
        widthSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 2000, 10));
        add(widthSpinner);

        add(new JLabel("Height:"));
        heightSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 2000, 10));
        add(heightSpinner);

        add(new JLabel("Red value:"));
        redSlider = new JSlider(0, 255);
        add(redSlider);

        add(new JLabel("Green value:"));
        greenSlider = new JSlider(0, 255);
        add(greenSlider);

        add(new JLabel("Blue value:"));
        blueSlider = new JSlider(0, 255);
        add(blueSlider);

        applyButton = new JButton("Apply Changes");
        add(applyButton);
    }
    

    /*
     * EFFECTS: Apply the specified changes, then close the edit panel
     */
    private void applyChanges(Boolean isNewDrawing) {

        if (isNewDrawing) {
            Drawing d = new Drawing(titleField.getText(), 
                                    (int) widthSpinner.getValue(),
                                    (int) heightSpinner.getValue(),
                                    new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));

            guiFrame.addDrawing(d);
        } else {
            selectedDrawing.setTitle(titleField.getText());
            selectedDrawing.setWidth((int) widthSpinner.getValue());
            selectedDrawing.setHeight((int) heightSpinner.getValue());
            selectedDrawing.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
        }

        guiFrame.switchToGalleryPanel();
    }


    /*
     * EFFECTS: Load the selected drawing's fields into the input fields
     */
    private void loadFields() {
        titleField.setText(selectedDrawing.getTitle());
        widthSpinner.setValue(selectedDrawing.getWidth());
        heightSpinner.setValue(selectedDrawing.getHeight());

        Color color = selectedDrawing.getColor();

        redSlider.setValue(color.getRed());
        greenSlider.setValue(color.getGreen());
        blueSlider.setValue(color.getBlue());
    }
}
