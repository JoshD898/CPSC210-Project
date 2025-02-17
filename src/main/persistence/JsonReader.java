package persistence;

import org.json.JSONObject;

import model.Drawing;
import model.Gallery;

// Represents a reader that reads gallery and drawing from JSON data stored in file
// ATTRIBUTION: This code is based on the JSON Serialization example project https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    
    /*
     * REQUIRES: source file exists in ./data
     * EFFECTS: constructs reader to read data from source file
     */
    public JsonReader(String source) {

    }

    /*
     * EFFECTS: read gallery from file and return it 
     */
    public Gallery readGallery() {
        return null;
    }

    /*
     * EFFECTS: read selected drawing from file and return it
     */
    public Drawing readSelectedDrawing() {
        return null;
    }

    /*
     * REQUIRES: source file exists in ./data
     * EFFECTS: reads source file as string then returns it
     */
    private String readFile(String source) {
        return null; 
    }

    /*
     * EFFECTS: parses selected drawing from JSON object and return it
     */
    private Drawing parseSelectedDrawing(JSONObject jsonObject) {
        return null;
    }

    /*
     * EFFECTS: parses gallery from JSON object and return it
     */
    private Gallery parseGallery(JSONObject jsonObject) {
        return null;
    }

    /*
     * MODIFIES: g
     * EFFECTS: parses drawings from JSON object and adds them to gallery
     */
    private void addDrawings(Gallery g, JSONObject jsonObject) {

    }

    /*
     * MODIFIES: g
     * EFFECTS: parses drawing from JSON object and adds it to gallery
     */
    private void addDrawing(Gallery g, JSONObject jsonObject) {
        
    }
}
