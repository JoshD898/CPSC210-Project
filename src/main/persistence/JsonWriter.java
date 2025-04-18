package persistence;

import model.Drawing;
import model.Event;
import model.EventLog;
import model.Gallery;

import java.io.*;
import org.json.JSONObject;

// Represents a writer that writes JSON representations of gallery and selected drawing to file
// ATTRIBUTION: This code is based on the JSON Serialization example project https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;
 
    /*
     * EFFECTS: constructs writer to write to destination file
     */
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /*
     * MODIFIES: this
     * EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of gallery and selected drawing to file
     */
    public void write(Gallery g, Drawing d) {
        JSONObject json = g.toJson();
        if (d == null) {
            json.put("selectedDrawingTitle", JSONObject.NULL);
        } else {
            json.put("selectedDrawingTitle", d.getTitle());
        }
        saveToFile(json.toString(TAB));
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes writer
     */
    public void close() {
        writer.close();
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes string to file
     */
    private void saveToFile(String json) {
        EventLog.getInstance().logEvent(new Event("Gallery saved to file"));
        writer.print(json);
    }
}
