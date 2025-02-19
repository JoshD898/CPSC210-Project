package persistence;

import model.Drawing;
import model.Gallery;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderEmptyGalleryNullDrawing() {
        try {
            JsonReader reader = new JsonReader("./data/test/testReaderEmptyGalleryNullDrawing.json");
            Gallery gallery = reader.readGallery();
            String selectedDrawingTitle = reader.readSelectedDrawingTitle();
            assertEquals(gallery.getDrawingList().size(), 0);
            assertNull(selectedDrawingTitle);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        
    }

    @Test
    void testReaderGeneralGalleryAndDrawing() {
        try {
            JsonReader reader = new JsonReader("./data/test/testReaderGeneralGalleryAndDrawing.json");
            Gallery gallery = reader.readGallery();
            String selectedDrawingTitle = reader.readSelectedDrawingTitle();
            Drawing selectedDrawing = gallery.getDrawing(selectedDrawingTitle);
            assertEquals(gallery.getDrawingList().size(), 2);
            assertTrue(gallery.getDrawingList().get(0).getTitle().equals("Sunrise"));
            assertTrue(selectedDrawing.getTitle().equals("Sunrise"));
            selectedDrawing.setTitle("Sunset");
            assertTrue(gallery.getDrawingList().get(0).getTitle().equals("Sunset"));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
        
    }

    @SuppressWarnings("unused")
    @Test
    void testReaderNonexistentFile() {
        try {
            JsonReader reader = new JsonReader("notAFile.json");
            Gallery gallery = reader.readGallery();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
        
    }
}
