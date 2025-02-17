package persistence;

import model.Drawing;
import model.Gallery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderEmptyGalleryNullDrawing() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGalleryNullDrawing.json");
        Gallery gallery = reader.readGallery();
        Drawing selectedDrawing = reader.readSelectedDrawing();
        assertEquals(gallery.getDrawingList().size(), 0);
        assertNull(selectedDrawing);
    }

    @Test
    void testReaderGeneralGalleryAndDrawing() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGalleryAndDrawing.json");
        Gallery gallery = reader.readGallery();
        Drawing selectedDrawing = reader.readSelectedDrawing();
        assertEquals(gallery.getDrawingList().size(), 2);
        assertTrue(gallery.getDrawingList().get(0).getTitle().equals("Sunrise"));
        assertTrue(selectedDrawing.getTitle().equals("Sunrise"));
        selectedDrawing.setTitle("Sunset");
        assertTrue(gallery.getDrawingList().get(0).getTitle().equals("Sunset"));
    }
}
