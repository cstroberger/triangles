package com.stroberger.chris.cherwell.test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Vector;

import org.junit.Test;

import com.stroberger.chris.cherwell.puzzle.TriangleException;
import com.stroberger.chris.cherwell.puzzle.TriangleMap;

public class TriangleMapTest {

    @Test
    public void testGetTriangleCoordinates() {
        try {
            Vector<Point> vertices = TriangleMap.getTriangleCoordinates("A1");
            assertTrue("Wrong number of vertices returned : " + vertices.size(), vertices.size() == 3);
            assertTrue("Expected vertex missing.", vertices.contains(new Point(0,0) ) );
            assertTrue("Expected vertex missing.", vertices.contains(new Point(10,10) ) );
            assertTrue("Expected vertex missing.", vertices.contains(new Point(0,10) ) );
        } catch (TriangleException e) {
            fail("TriangleException received : " + e.getMessage() );
        }
        
        try {
            Vector<Point> vertices = TriangleMap.getTriangleCoordinates("F12");
            assertTrue("Wrong number of vertices returned : " + vertices.size(), vertices.size() == 3);
            assertTrue("Expected vertex missing.", vertices.contains(new Point(50,50) ) );
            assertTrue("Expected vertex missing.", vertices.contains(new Point(60,60) ) );
            assertTrue("Expected vertex missing.", vertices.contains(new Point(60,50) ) );
        } catch (TriangleException e) {
            fail("TriangleException received : " + e.getMessage() );
        }

        try {
            Vector<Point> vertices = TriangleMap.getTriangleCoordinates("B9");
            assertTrue("Wrong number of vertices returned : " + vertices.size(), vertices.size() == 3);
            assertTrue("Expected vertex missing.", vertices.contains(new Point(40,10) ) );
            assertTrue("Expected vertex missing.", vertices.contains(new Point(50,20) ) );
            assertTrue("Expected vertex missing.", vertices.contains(new Point(40,20) ) );
        } catch (TriangleException e) {
            fail("TriangleException received : " + e.getMessage() );
        }
        
        try {
            Vector<Point> vertices = TriangleMap.getTriangleCoordinates("G9");
            fail("Should not have reached this code. Coordinates were out of bounds.");
        } catch (TriangleException e) {}
        
        try {
            Vector<Point> vertices = TriangleMap.getTriangleCoordinates("E13");
            fail("Should not have reached this code. Coordinates were out of bounds.");
        } catch (TriangleException e) {}
    }

    @Test
    public void testGetTriangleLocationTag() {
        Vector<Point> vertices;
        String tag;
        
        vertices = new Vector<>();
        vertices.add(new Point(0,0));
        vertices.add(new Point(10,0));
        vertices.add(new Point(10,10));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            assertEquals(tag, "A2");
        } catch (TriangleException e) {
            fail("TriangleException received : " + e.getMessage() );
        }
        
        vertices = new Vector<>();
        vertices.add(new Point(0,60));
        vertices.add(new Point(10,60));
        vertices.add(new Point(0,50));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            assertEquals(tag, "F1");
        } catch (TriangleException e) {
            fail("TriangleException received : " + e.getMessage() );
        }
        
        vertices = new Vector<>();
        vertices.add(new Point(60,10));
        vertices.add(new Point(60,0));
        vertices.add(new Point(50,0));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            assertEquals(tag, "A12");
        } catch (TriangleException e) {
            fail("TriangleException received : " + e.getMessage() );
        }
                
        vertices = new Vector<>();
        vertices.add(new Point(30,10));
        vertices.add(new Point(60,0));
        vertices.add(new Point(50,0));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            fail("Should not have reached this code. Vertices form a bad triangle.");
        } catch (TriangleException e) {}

        vertices = new Vector<>();
        vertices.add(new Point(40,40));
        vertices.add(new Point(50,50));
        vertices.add(new Point(49,50));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            fail("Should not have reached this code. Vertices form a bad triangle.");
        } catch (TriangleException e) {}
        
        vertices = new Vector<>();
        vertices.add(new Point(40,50));
        vertices.add(new Point(50,40));
        vertices.add(new Point(50,50));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            fail("Should not have reached this code. Vertices form a bad triangle.");
        } catch (TriangleException e) {}
        
        vertices = new Vector<>();
        vertices.add(new Point(80,10));
        vertices.add(new Point(80,0));
        vertices.add(new Point(70,0));
        try {
            tag = TriangleMap.getTriangleLocationTag(vertices);
            fail("Should not have reached this code. Vertices were out of bounds.");
        } catch (TriangleException e) {}
    }
}
