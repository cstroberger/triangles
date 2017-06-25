package com.stroberger.chris.cherwell.puzzle;

import java.awt.Point;
import java.util.Vector;

/**
 * This class encapsulates the logic defining the size and display scale of the 
 * grid of triangles.  Methods of this class do bounds checking on passed in parameters.
 * 
 * @author Chris Stroberger
 *
 */
public class TriangleMap {
    private static final int MIN_COL = 1;
    private static final int MAX_COL = 12;

    private static final char MIN_ROW = 'A';
    private static final char MAX_ROW = 'F';
    
    public static final int SCALE = 10;

    /**
     * Return the vertices for a triangle at the given map location.
     * 
     * @param locationTag    The location in the map of the triangle in question.  This is a string
     *                       of format "Xnn" where "X" is the letter of the row in the map, and "nn"
     *                       (possibly a single digit) is the column number. 
     * @return  The vertices of the triangle at that location.
     * @throws TriangleException
     */
    public static Vector<Point> getTriangleCoordinates(String locationTag) throws TriangleException {
        char row = locationTag.charAt(0);
        int col = Integer.valueOf(locationTag.substring(1));

        // Got a good column value?
        if ((col > MAX_COL) || (col < MIN_COL)) {
            throw new TriangleException("Column value is out of bounds.");
        }

        // Got a good row value?
        char upper = Character.toUpperCase(row);
        if ((upper < MIN_ROW) || (upper > MAX_ROW)) {
            throw new TriangleException("Row value is out of bounds.");
        }

        // Use the location tag to instantiate the MapTriangle,
        // then ask it for its vertices.
        MapTriangle triangle = new MapTriangle(locationTag);
        return triangle.getVertices();
    }
    
    
    /**
     * Return the map location of a triangle with the given vertices.
     * 
     * @param coords  The vertices for the triangle in question.
     * @return    The location tag of the specified triangle.
     * @throws TriangleException
     */
    public static String getTriangleLocationTag(Vector<Point> coords) throws TriangleException {
        // Make sure we are not out of bounds.
        for (Point vertex : coords) {
            if ( (vertex.x > MAX_COL * SCALE / 2) || (vertex.x < 0) ||
                 (vertex.y > MAX_ROW * SCALE ) || (vertex.y < 0) ) {
                throw new TriangleException("Vetex is out of bounds: " + vertex);
            }
        }
        
        // Use the vertices to instantiate the MapTriangle,
        // then ask it for its location tag.
        MapTriangle triangle = new MapTriangle(coords);
        return triangle.getLocationTag();
    }
}
