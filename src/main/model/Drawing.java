package model;

import java.awt.Color;

// Represent a drawing having a title, color, width (in pixels) and height (in pixels)
public class Drawing {

    /*
     * REQUIRES: width and height both be >= 0    
     * EFFECTS: constructs a new Drawing object
     */
    public Drawing(String title, int width, int height, Color color) {
    }

    public String getTitle() {
        return ""; // stub
    }

    public Color getColor() {
        return new Color(0,0,0);
    }

    public int getWidth() {
        return 0; // stub
    }

    public int getHeight() {
        return 0; // stub
    }

    /*
     * EFFECTS: return a string representation of the drawing completion status
     */
    public String getStatus() {
        return ""; // stub
    }
    
    /*
     * MODIFIES: this
     * EFFECTS: title of drawing is set to newTitle
     */
    public void setTitle(String newTitle) {
    }

    /*
     * MODIFIES: this
     * EFFECTS: color is set to newColor
     */
    public void setColor(Color newColor) {
    }

    /*
     * MODIFIES: this
     * EFFECTS: width is set to newWidth
     */
    public void setWidth(int newWidth) {
    }

    /*
     * MODIFIES: this
     * EFFECTS: height is set to newHeight
     */
    public void setHeight(int newHeight) {
    }

    /*
     * MODIFIES: this
     * EFFECTS:  isComplete is set to the opposite of its current value
     */
    public void changeStatus() {
    }

    /*
     * EFFECTS: return a string representation of drawing
     */
    public String toString() {
        return ""; // stub
    }    
}
