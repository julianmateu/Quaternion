//: object/Vector3D.java

import java.lang.*;

/**
 * A Java Class to operate with Vectors in R3.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class Vector3D {

    private static final double MIN_TOLERANCE = 1e-10;

    //************ fields ************//
    private double x, y, z;

    /**
     * Constructor
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Method to add two Vector3Ds.
     *
     * @return It returns a new Vector3D with the result of the sum.
     */
    public Vector3D add(Vector3D addend) {
        return new Vector3D(
                this.x() + addend.x(),
                this.y() + addend.y(),
                this.z() + addend.z());
    }

    /**
     * Method to subtract two Vector3Ds.
     *
     * @return It returns a new Vector3D with the result of the subtraction.
     */
    public Vector3D subtract(Vector3D subtrahend) {
        return new Vector3D(
                this.x() - subtrahend.x(),
                this.y() - subtrahend.y(),
                this.z() - subtrahend.z());
    }

    /**
     * Method to multiply a Vector3D by a scalar.
     *
     * @return Returns a new Vector3D with the result of the product by scalar.
     */
    public Vector3D multiply(double scalar) {
        return new Vector3D(
                this.x() * scalar,
                this.y() * scalar,
                this.z() * scalar);
    }

    /**
     * Method to calculate the norm of a Vector3D.
     *
     * @return Returns the norm of the vector.
     */
    public double norm() {
        return Math.sqrt(this.x() * this.x() +
                this.y() * this.y() +
                this.z() * this.z());
    }

    /**
     * Method to normalize a Vector3D.
     *
     * @return Returns a new Vector3D with unit norm, un the same direction.
     */
    public Vector3D normalize() {

        double norm = this.norm();

        if (norm < MIN_TOLERANCE) {
            throw new IllegalArgumentException("Trying to normalize a zero norm vector");
        }

        return new Vector3D(
                this.x(),
                this.y(),
                this.z()).multiply(1.0 / norm);
    }

    /**
     * Method to assert if to Vector3Ds are equal.
     *
     * @return Returns True if they are equal, and False otherwise.
     */
    public boolean equals(Vector3D compared) {

        return this.x() == compared.x() && this.y() == compared.y() && this.z() ==
                compared.z();
    }

    /**
     * Method to assert if to Vector3Ds are equal.
     *
     * @return Returns True if they are equal, and False otherwise.
     */
    @Override
    public boolean equals(Object compared) {
        return compared instanceof Vector3D && this.equals((Vector3D) compared);
    }

    /**
     * Method to convert a Vector3D to a string.
     *
     * @return Returns the Vector3D in the form ( x ; y ; z ).
     */
    @Override
    public String toString() {
        return "( " + this.x() + " ; " + this.y() + " ; " +
                this.z() + " )";
    }

    //************ Getters & Setters ************//

    /**
     * Getter for the X coordinate.
     */
    public double x() {
        return this.x;
    }

    /**
     * Getter for the Y coordinate.
     */
    public double y() {
        return this.y;
    }

    /**
     * Getter for the z coordinate.
     */
    public double z() {
        return this.z;
    }

    /**
     * Setter for the X coordinate.
     */
    private void setX(double x) {
        this.x = x;
    }

    /**
     * Setter for the Y coordinate.
     */
    private void setY(double y) {
        this.y = y;
    }

    /**
     * Setter for the Z coordinate.
     */
    private void setZ(double z) {
        this.z = z;
    }
} 
