package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.awt.Color;
import org.json.*;

import model.Drawing;
import model.EventLog;
import model.Event;
import model.Gallery;

// Represents a reader that reads gallery and drawing from JSON data stored in file
// ATTRIBUTION: This code is based on the JSON Serialization example project https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {

    private String source;
    
    /*
     * REQUIRES: source file exists in ./data
     * EFFECTS: constructs reader to read data from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: read gallery from file and return it 
     */
    public Gallery readGallery() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonobject = new JSONObject(jsonData);

        EventLog.getInstance().logEvent(new Event("Gallery loaded from file"));

        return parseGallery(jsonobject);
    }

    /*
     * EFFECTS: read selected drawing from file and return it
     */
    public String readSelectedDrawingTitle() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        String title = jsonObject.optString("selectedDrawingTitle");
        if (title.isEmpty()) {
            return null;
        }
        return title;
    }

    /*
     * REQUIRES: source file exists in ./data
     * EFFECTS: reads source file as string then returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /*
     * EFFECTS: parses gallery from JSON object and return it
     */
    private Gallery parseGallery(JSONObject jsonObject) {
        Gallery gallery = new Gallery();
        JSONArray jsonArray = jsonObject.getJSONArray("gallery");
        for (Object json : jsonArray) {
            JSONObject drawingJson = (JSONObject) json;
            gallery.addDrawing(parseDrawing(drawingJson));
        }
        return gallery;
    }

    /*
     * MODIFIES: g
     * EFFECTS: parses drawing from JSON object and returns it
     */
    private Drawing parseDrawing(JSONObject drawingJson) {
        String title = drawingJson.getString("title");
        int width = drawingJson.getInt("width");
        int height = drawingJson.getInt("height");
        int red = drawingJson.getInt("red");
        int green = drawingJson.getInt("green");
        int blue = drawingJson.getInt("blue");
        boolean isComplete = drawingJson.getBoolean("isComplete");

        Drawing d = new Drawing(title, width, height, new Color(red, green, blue));

        if (isComplete) {
            d.markAsComplete();
        }
        
        return d;
    }
}
