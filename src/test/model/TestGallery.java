package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.awt.Color;

public class TestGallery {

    Gallery g1;
    Drawing d1;
    Drawing d2;

    @BeforeEach
    void runBefore() {
        g1 = new Gallery();
        d1 = new Drawing("Sunrise", 200, 200, new Color(0,0,0));
        d2 = new Drawing("Ocean", 100, 500, new Color(100, 50, 60));
    }

    @Test
    void testConstructor() {
        assertEquals(g1.getDrawingList(), new ArrayList<>());
    }

    @Test
    void testAddDrawing() {
        assertEquals(g1.getDrawingList().size(), 0);
        g1.addDrawing(d1);
        assertEquals(g1.getDrawingList().size(), 1);
        g1.addDrawing(d2);
        assertEquals(g1.getDrawingList().size(), 2);
    }

    @Test
    void testGetInProgressList() {
        d1.changeStatus();
        assertEquals(d1.getStatus(), "Complete");
        assertEquals(d2.getStatus(), "In progress");
        g1.addDrawing(d1);
        g1.addDrawing(d2);
        assertEquals(g1.getDrawingList().size(), 2);
        
        assertEquals(g1.getInProgressList().size(), 1);
        assertTrue(g1.getInProgressList().contains(d2));
    }

    @Test
    void testGetCompletedList() {
        d1.changeStatus();
        assertEquals(d1.getStatus(), "Complete");
        assertEquals(d2.getStatus(), "In progress");
        g1.addDrawing(d1);
        g1.addDrawing(d2);
        assertEquals(g1.getDrawingList().size(), 2);
        
        assertEquals(g1.getCompleteList().size(), 1);
        assertTrue(g1.getCompleteList().contains(d1));
    }

    @Test
    void testContainsDrawingWithTitle() {
        assertFalse(g1.containsDrawingWithTitle("Sunrise"));
        g1.addDrawing(d1);
        assertTrue(g1.containsDrawingWithTitle("Sunrise"));
        assertFalse(g1.containsDrawingWithTitle("Sunset"));
    }

    @Test
    void testGetDrawing() {
        g1.addDrawing(d1);
        assertEquals(g1.getDrawing("Sunrise"), d1);
        g1.addDrawing(d2);
        assertEquals(g1.getDrawing("Sunrise"), d1);
        assertEquals(g1.getDrawing("Ocean"), d2);
    }      

    @Test
    void testRemoveDrawing() {
        g1.addDrawing(d1);
        assertEquals(g1.getDrawingList().size(), 1);
        g1.removeDrawing(d1);
        assertEquals(g1.getDrawingList().size(), 0);
    }

    @Test
    void testChangeStatus() {
        assertEquals(d1.getStatus(), "In progress");
        g1.addDrawing(d1);
        g1.changeStatus(d1);
        assertEquals(d1.getStatus(), "Complete");
    }

    @Test
    void testEditTitle() {
        assertEquals(d1.getTitle(), "Sunrise");
        g1.addDrawing(d1);
        g1.editTitle(d1, "Sunset");
        assertEquals(d1.getTitle(), "Sunset");
    }

    @Test
    void testEditDimensions() {
        assertEquals(d1.getWidth(), 200);
        assertEquals(d1.getHeight(), 200);
        g1.addDrawing(d1);
        g1.editDimensions(d1, 50, 90);
        assertEquals(d1.getWidth(), 50);
        assertEquals(d1.getHeight(), 90);
    }

    @Test
    void testEditColor() {
        assertEquals(d1.getColor(), new Color(0,0,0));
        g1.addDrawing(d1);
        g1.editColor(d1, new Color(100,50,80));
        assertEquals(d1.getColor(), new Color(100,50,80));
    }  
}
