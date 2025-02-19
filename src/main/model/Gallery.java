package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Represents a gallery containing an arbitrary number of drawings
public class Gallery {

    List<Drawing> drawingList;

    /*
     * EFFECTS: constructs a new Gallery object
     */
    public Gallery() {
        drawingList = new ArrayList<>();
    }

    /*
    * REQUIRES: the title of the new drawing must be unique
    * MODIFIES: this
    * EFFECTS: adds a new drawing to drawingList
    */
    public void addDrawing(Drawing d) {
        drawingList.add(d);
    }

    /*
     * EFFECTS: returns a list of all drawings in galley
     */
    public List<Drawing> getDrawingList() {
        return drawingList;
    }

    /*
    * EFFECTS: returns a list of all drawings that are marked as in progress
    */
    public List<Drawing> getInProgressList() {
        List<Drawing> arr = new ArrayList<>();
        for (Drawing d : drawingList) {
            if (d.getStatus().equals("In progress")) {
                arr.add(d);
            }
        }
        return arr;
    }

    /*
    * EFFECTS: returns a list of all drawings that are marked as complete
    */
    public List<Drawing> getCompleteList() {
        List<Drawing> arr = new ArrayList<>();
        for (Drawing d : drawingList) {
            if (d.getStatus().equals("Complete")) {
                arr.add(d);
            }
        }
        return arr;
    }

    /*
    * EFFECTS: returns true if a drawing with the given title exists in the list of drawings
    */
    public boolean containsDrawingWithTitle(String title) {
        for (Drawing d : drawingList) {
            if (d.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /*
    * REQUIRES: a drawing in the list of drawings has the corresponding title
    * EFFECTS: returns Drawing object with matching title from list of drawings
    */
    public Drawing getDrawing(String title) {
        int index = 0;

        for (int i = 0; i < drawingList.size(); i++) {
            if (drawingList.get(i).getTitle().equals(title)) {
                index = i;
            }
        }
        
        return drawingList.get(index);
    }

    /*
    * REQUIRES: d must be in the list of drawings
    * MODIFIES: this
    * EFFECTS: removes a drawing from the list of drawings
    */
    public void removeDrawing(Drawing d) {
        drawingList.remove(d);
    }

    /*
     * EFFECTS: return a JSON object representation of the gallery
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("gallery", drawingsToJson());
        return json;
    }

    /*
     * EFFECTS: return a JSON array representation of drawings in the gallery
     */
    private JSONArray drawingsToJson() {
        JSONArray jsonarray = new JSONArray();
        for (Drawing d: drawingList) {
            jsonarray.put(d.toJson());
        }
        return jsonarray;
    }
}
