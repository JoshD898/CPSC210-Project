package persistence;

import model.Drawing;
import model.Gallery;

import java.io.FileNotFoundException;

// Represents a writer that writes JSON representations of gallery and selected drawing to file
// ATTRIBUTION: This code is based on the JSON Serialization example project https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of gallery and selected drawing to file
    public void write(Gallery g, Drawing d) {
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }
}
