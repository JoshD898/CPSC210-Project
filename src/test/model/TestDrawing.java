package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDrawing {
    
    Drawing d1;
    Drawing d2;

    @BeforeEach
    void runBefore() {
        d1 = new Drawing("Sunrise", 200, 200, new Color(0,0,0));
        d2 = new Drawing("Ocean", 100, 500, new Color(100, 50, 60));
    }

    @Test
    void testConstructor() {
        assertEquals(d1.getTitle(), "Sunrise");
        assertEquals(d1.getWidth(), 200);
        assertEquals(d1.getHeight(), 200);
        assertEquals(d1.getColor(), new Color(0,0,0));
        assertEquals(d1.getStatus(), "In progress");

        assertEquals(d2.getTitle(), "Ocean");
        assertEquals(d2.getWidth(), 100);
        assertEquals(d2.getHeight(), 500);
        assertEquals(d2.getColor(), new Color(100,50,60));
        assertEquals(d2.getStatus(), "In progress");
    }

    @Test
    void testSetTitle() {
        assertEquals(d1.getTitle(), "Sunrise");
        d1.setTitle("Sunset");
        assertEquals(d1.getTitle(), "Sunset");
    }

    @Test
    void testSetColor() {
        assertEquals(d1.getColor(), new Color(0,0,0));
        d1.setColor(new Color(255, 255,255));
        assertEquals(d1.getColor(), new Color(255,255,255));
    }

    @Test
    void testSetWidth() {
        assertEquals(d1.getWidth(), 200);
        d1.setWidth(0);
        assertEquals(d1.getWidth(), 0);;
    }

    @Test
    void testSetHeight() {
        assertEquals(d1.getHeight(), 200);
        d1.setHeight(1000);
        assertEquals(d1.getHeight(), 1000);;
    }

    @Test
    void testChangeStatus() {
        assertEquals(d1.getStatus(), "In progress");
        d1.markAsComplete();
        assertEquals(d1.getStatus(), "Complete");
        d1.markAsComplete();
        assertEquals(d1.getStatus(), "Complete");
    }

    @Test
    void testToString() {
        assertEquals(d1.toString(), 
                     "Title: Sunrise, Width: 200px, Height: 200px, Color: (0,0,0), Status: In progress");

        d2.markAsComplete();
        assertEquals(d2.toString(), 
                     "Title: Ocean, Width: 100px, Height: 500px, Color: (100,50,60), Status: Complete");
    }
}
