package model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

// Represents a gallery containing an arbitrary number of drawings
public class Gallery {

    /*
     * EFFECTS: constructs a new Gallery object
     */
    public Gallery() {
    }

    /*
    * MODIFIES: this
    * EFFECTS: adds a new drawing to the list of drawings
    */
    public void addDrawing(Drawing d){
    }

    /*
    * REQUIRES: size of drawing list must be >= 1
    * MODIFIES: this
    * EFFECTS: removes a drawing from the list of drawings
    */
    public void removeDrawing(Drawing d) {
    }

    /*
    * MODIFIES: this, d
    * EFFECTS: changes the completion status of the drawing
    */
    public void changeStatus(Drawing d) {
    }

    /*
    * MODIFIES: this, d
    * EFFECTS: changes the title of the drawing
    */
    public void editTitle(Drawing d, String newTitle) {

    }

    /*
    * REQUIRES: newWidth and newHeight both >= 0
    * MODIFIES: this, d
    * EFFECTS: changes the width and height of the given drawing
    */
    public void editDimensions(Drawing d, int newWidth, int newHeight) {

    }

    /*
    * MODIFIES: this, d
    * EFFECTS: changes the color of the given drawing
    */
    public void editColor(Drawing d, Color newColor) {

    }

    /*
    * EFFECTS: returns true if a drawing with the given title exists in the list of drawings
    */
    public boolean containsDrawingWithTitle(String title) {
        return true; // stub
    }

    /*
    * EFFECTS: returns Drawing object with matching title from list of drawings, or throws an exception 
    */
    public Drawing findDrawing(String title) {
        return new Drawing("stub", 0, 0, null);
    }

    /*
    * EFFECTS: returns a list of all drawings that are marked as in progress
    */
    public List<Drawing> inProgress() {
        return new ArrayList<>(); // stub
    }

    /*
    * EFFECTS: returns a list of all drawings that are marked as complete
    */
    public List<Drawing> complete() {
        return new ArrayList<>(); // stub
    }
}
