package model;

import java.awt.Color;

import org.json.JSONObject;

// Represent a drawing having a title, color, width (in pixels) and height (in pixels)
public class Drawing {

    private String title;
    private int width;
    private int height;
    private Color color;
    private Boolean isComplete;

    /*
     * REQUIRES: width and height both be >= 0
     * EFFECTS: constructs a new Drawing object
     */
    public Drawing(String title, int width, int height, Color color) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.color = color;
        this.isComplete = false;
    }

    public String getTitle() {
        return title;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*
     * EFFECTS: return a string representation of the drawing completion status
     */
    public String getStatus() {
        if (isComplete) {
            return "Complete";
        }
        return "In progress";
    }

    /*
     * MODIFIES: this
     * EFFECTS: title of drawing is set to newTitle
     */
    public void setTitle(String newTitle) {
        EventLog.getInstance().logEvent(new Event("Modified drawing: " + title));
        title = newTitle;
    }

    /*
     * MODIFIES: this
     * EFFECTS: color is set to newColor
     */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /*
     * MODIFIES: this
     * EFFECTS: width is set to newWidth
     */
    public void setWidth(int newWidth) {
        width = newWidth;
    }

    /*
     * MODIFIES: this
     * EFFECTS: height is set to newHeight
     */
    public void setHeight(int newHeight) {
        height = newHeight;
    }

    /*
     * MODIFIES: this
     * EFFECTS: isComplete is set to true
     */
    public void markAsComplete() {
        isComplete = true;
    }

    /*
     * EFFECTS: return a string representation of drawing
     */
    public String toString() {
        return String.format("Title: %s, Width: %dpx, Height: %dpx, Color: (%d,%d,%d), Status: %s",
                title,
                width,
                height,
                color.getRed(),
                color.getGreen(),
                color.getBlue(),
                getStatus());
    }

    /*
     * EFFECTS: return a JSON object representation of drawing
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("width", width);
        json.put("height", height);
        json.put("red", color.getRed());
        json.put("green", color.getGreen());
        json.put("blue", color.getBlue());
        json.put("isComplete", isComplete);
        return json;
    }
}
