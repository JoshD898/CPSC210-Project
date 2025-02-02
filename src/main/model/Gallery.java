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
    * REQUIRES: the title of the new drawing must be unique
    * MODIFIES: this
    * EFFECTS: adds a new drawing to the list of drawings
    */
    public void addDrawing(Drawing d){
    }

        /*
     * EFFECTS: returns a list of all drawings in galley
     */
    public List<Drawing> getDrawingList() {
        return new ArrayList<>(); // stub
    }

    /*
    * EFFECTS: returns a list of all drawings that are marked as in progress
    */
    public List<Drawing> getInProgressList() {
        return new ArrayList<>(); // stub
    }

    /*
    * EFFECTS: returns a list of all drawings that are marked as complete
    */
    public List<Drawing> getCompleteList() {
        return new ArrayList<>(); // stub
    }

    /*
    * EFFECTS: returns true if a drawing with the given title exists in the list of drawings
    */
    public boolean containsDrawingWithTitle(String title) {
        return true; // stub
    }

    /*
    * REQUIRES: a drawing in the list of drawings has the corresponding title
    * EFFECTS: returns Drawing object with matching title from list of drawings
    */
    public Drawing getDrawing(String title) {
        return new Drawing("stub", 0, 0, null);
    }

    /*
    * REQUIRES: d must be in the list of drawings
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
    * REQUIRES: newTitle is not already the title of another drawing in the list of drawings
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
}
