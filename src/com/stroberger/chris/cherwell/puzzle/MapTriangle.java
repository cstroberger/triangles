package com.stroberger.chris.cherwell.puzzle;

import java.awt.Point;
import java.util.Vector;

import com.stroberger.chris.cherwell.puzzle.LocationTag;

/**
 * The MapTriangle class is responsible for defining the shape of 
 * a legal triangle.
 * 
 * @author Chris Stroberger
 *
 */
public class MapTriangle {

    private LocationTag myLocationTag;
    private Vector<Point> myVertices;
    
    /**
     * Construct a MapTriangle by its vertices.
     * 
     * @param coordinates
     * @throws TriangleException 
     */
    public MapTriangle(Vector<Point> coordinates) throws TriangleException {
        if (coordinates.size() != 3) {
            throw new TriangleException("Did not receive three vertices.");
        }

        myVertices = coordinates;

        int vertical_coord = -1;
        int horizontal_coord = -1;
        
        // Now set the location tag.
        Point vertex = myVertices.elementAt(0);
        int max_x = vertex.x;
        int min_x = vertex.x;
        int max_y = vertex.y;
        int min_y = vertex.y;
        
        // Find the min and max along both axes, and the vertical line.
        for (int idx = 1; idx < 3; idx++) {
            vertex = myVertices.elementAt(idx);
            if (vertex.x > max_x){
                max_x = vertex.x;
            }
            else if (vertex.x < min_x) {
                min_x = vertex.x;
            }
            else if ( (vertex.x == max_x) || (vertex.x == min_x) ){
                vertical_coord = vertex.x;
            }
            
            if (vertex.y > max_y){
                max_y = vertex.y;
            }
            else if (vertex.y < min_y) {
                min_y = vertex.y;
            }
            else if ( (vertex.y == max_y) || (vertex.y == min_y) ){
                horizontal_coord = vertex.y;
            }
        }
        
        // Is this even a legal triangle?
        if ( (max_x - min_x != TriangleMap.SCALE) ||
             (max_y - min_y != TriangleMap.SCALE) ||
             (vertical_coord == -1) ||
             (horizontal_coord == -1) ||
             !(myVertices.contains(new Point(min_x,min_y)) && myVertices.contains(new Point(max_x,max_y) ) ) ) {
            throw new TriangleException("Vertices do not specify a legal triangle.");
        }
        
        // Translate pixel coordinates to row/column coordinates.
        int row_coord = max_y / TriangleMap.SCALE;
        int col_coord = (max_x / TriangleMap.SCALE) * 2;
        
        // If the vertical edge of the triangle is not at the right,
        //  we have a lower triangle.  Decrement the column index by one.
        if (max_x != vertical_coord) col_coord--;
                
        myLocationTag = new LocationTag(row_coord, col_coord);
    }

    /**
     * Construct a MapTriangle by its location tag.
     * 
     * @param tag
     */
    public MapTriangle(String tag) {
        myLocationTag = new LocationTag(tag);
        
        // Now set the vertices.
        int row = myLocationTag.getRowIndex();
        int col = myLocationTag.getColIndex();
        
        myVertices = new Vector<>();

        int max_y = row * TriangleMap.SCALE;
        int min_y = max_y - TriangleMap.SCALE;
                
        int max_x = (int)Math.ceil(col * 0.5) * TriangleMap.SCALE;
        int min_x = max_x - TriangleMap.SCALE;
        
        // The hypotenuse endpoints are the same for upper and lower triangles.
        myVertices.addElement( new Point(min_x, min_y) );
        myVertices.addElement( new Point(max_x, max_y) );
        
        // Now set the vertex of the right angle.
        if (col % 2 == 0) {
            // It's an upper triangle
            myVertices.add( new Point(max_x, min_y) );
        }
        else {
            // It's a lower triangle
            myVertices.add( new Point(min_x, max_y) );
        }
    }

    /**
     * Return this triangle's locationTag.
     * 
     * @return
     */
    public String getLocationTag() {
        return myLocationTag.getString();
    }

    /**
     * Return the vertices of this triangle.
     * 
     * @return
     */
    public Vector<Point> getVertices() {
        return myVertices;
    }
}
