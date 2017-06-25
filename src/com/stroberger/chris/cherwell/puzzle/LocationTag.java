package com.stroberger.chris.cherwell.puzzle;

/**
 * This class encapsulates the logic around constructing and
 * deconstructing a triangle's location tag.
 * 
 * @author Chris Stroberger
 *
 */
public class LocationTag {

    private String myTag = " ";
    private char myRowName = ' ';
    private int myRowIndex = 0;
    private int myColIndex = 0;

    public LocationTag(int row, int col) {
        this.setIndices(row, col);
    }

    public LocationTag(String tag) {
        myTag = tag;
        myRowName = Character.toUpperCase( tag.charAt(0) );
        myRowIndex = myRowName - 'A' + 1;
        myColIndex = Integer.valueOf( tag.substring(1) );
    }

    public int getRowIndex() {
        return myRowIndex;
    }

    public void setIndices(int row, int col) {
        this.myRowIndex = row;
        this.myRowName = (char)('A' + myRowIndex - 1 );
        this.myColIndex = col;
        generateMyTag();
    }

    public int getColIndex() {
        return myColIndex;
    }

    public String getString() {
        return myTag;
    }

    public char getRowName() {
        return myRowName;
    }

    private void generateMyTag() {
        myTag = Character.toString(myRowName) + myColIndex;
    }
}
