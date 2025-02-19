package persistence;

import model.Drawing;
import model.Gallery;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/illegal\0filename.json");
            writer.open();

            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGalleryNullDrawing() {
        try {
            Gallery gallery = new Gallery();

            JsonWriter writer = new JsonWriter("./data/test/testWriterEmptyGalleryNullDrawing.json");
            writer.open();
            writer.write(gallery, null);
            writer.close();

            JsonReader reader = new JsonReader("./data/test/testWriterEmptyGalleryNullDrawing.json");
            gallery = reader.readGallery();
            String selectedDrawingTitle = reader.readSelectedDrawingTitle();

            assertEquals(gallery.getDrawingList().size(), 0);
            assertNull(selectedDrawingTitle);

        } catch (IOException e) {
            fail("IOEception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGalleryAndDrawing() {
        try {
            Drawing d1 = new Drawing("Sunrise", 200, 200, new Color(0,0,0));
            Drawing d2 = new Drawing("Ocean", 100, 500, new Color(100, 50, 60));
            Gallery gallery = new Gallery();
            gallery.addDrawing(d1);
            gallery.addDrawing(d2);

            JsonWriter writer = new JsonWriter("./data/test/testWriterGeneralGalleryAndDrawing.json");
            writer.open();
            writer.write(gallery, d1);
            writer.close();

            JsonReader reader = new JsonReader("./data/test/testWriterGeneralGalleryAndDrawing.json");
            gallery = reader.readGallery();
            String selectedDrawingTitle = reader.readSelectedDrawingTitle();

            assertEquals(gallery.getDrawingList().size(), 2);
            assertTrue(selectedDrawingTitle.equals("Sunrise"));
            
        } catch (IOException e) {
            fail("IOEception should not have been thrown");
        }
    }
}
