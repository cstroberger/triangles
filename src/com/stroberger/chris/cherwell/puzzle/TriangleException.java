package com.stroberger.chris.cherwell.puzzle;

/**
 * Something is generically wrong with the triangle.
 * 
 * @author Chris Stroberger
 *
 */
public class TriangleException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public TriangleException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
