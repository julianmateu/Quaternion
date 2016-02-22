//: object/RotationMatrix.java

/**
 * A Java Class to operate with Rotation Matrices in R3.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class RotationMatrix {

    private static final int SIZE = 3;

    public enum Axis {
        X_AXIS,
        Y_AXIS,
        Z_AXIS
    }

    ;


    //************ fields ************//
    private double[][] matrix = new double[SIZE][SIZE];

    /**
     * Constructor with angle and axis.
     *
     * @param angle Angle of counterclockwise rotation in degrees.
     * @param axis  Vector3D that represents the axis of rotation.
     */
    public RotationMatrix(double angle, Vector3D axis) {
        double c = Math.cos(Math.toRadians(angle));
        double s = Math.sin(Math.toRadians(angle));

        if (axis.norm() != 1) {
            axis = axis.normalize();
        }

        matrix[0][0] = c + axis.x() * axis.x() * (1 - c);
        matrix[0][1] = axis.x() * axis.y() * (1 - c) - axis.z() * s;
        matrix[0][2] = axis.x() * axis.z() * (1 - c) + axis.y() * s;

        matrix[1][0] = axis.y() * axis.x() * (1 - c) + axis.z() * s;
        matrix[1][1] = c + axis.y() * axis.y() * (1 - c);
        matrix[1][2] = axis.y() * axis.z() * (1 - c) - axis.x() * s;

        matrix[2][0] = axis.z() * axis.x() * (1 - c) - axis.y() * s;
        matrix[2][1] = axis.z() * axis.y() * (1 - c) + axis.x() * s;
        matrix[2][2] = c + axis.z() * axis.z() * (1 - c);
    }

    /**
     * Constructor with array.
     *
     * @param matrix A double[3][3] that contains the elements of the matrix.
     */
    public RotationMatrix(double[][] matrix) {

        if (matrix == null) {
            throw new NullPointerException();
        }


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    /**
     * Constructor for basic rotation around X axis.
     *
     * @param angle Angle in degrees of counterclockwise rotation around X axis.
     */
    public static RotationMatrix basicRotationX(double angle) {
        return new RotationMatrix(angle, new Vector3D(1, 0, 0));
    }

    /**
     * Constructor for basic rotation around Y axis.
     *
     * @param angle Angle in degrees of counterclockwise rotation around Y axis.
     */
    public static RotationMatrix basicRotationY(double angle) {
        return new RotationMatrix(angle, new Vector3D(0, 1, 0));
    }

    /**
     * Constructor for basic rotation around Z axis.
     *
     * @param angle Angle in degrees of counterclockwise rotation around Z axis.
     */
    public static RotationMatrix basicRotationZ(double angle) {
        return new RotationMatrix(angle, new Vector3D(0, 0, 1));
    }


    /**
     * Method to rotate a vector.
     *
     * @param vector Vector3D to rotate.
     * @return Result of the rotation.
     */
    public Vector3D rotate(Vector3D vector) {
        double[] result = new double[]{0,0,0};

        result[0] = this.getElement(0,0) * vector.x() +
                    this.getElement(0,1) * vector.y() +
                    this.getElement(0,2) * vector.z();

        result[1] = this.getElement(1,0) * vector.x() +
                    this.getElement(1,1) * vector.y() +
                    this.getElement(1,2) * vector.z();

        result[2] = this.getElement(2,0) * vector.x() +
                    this.getElement(2,1) * vector.y() +
                    this.getElement(2,2) * vector.z();

        return new Vector3D(result);
    }

    /**
     * Method to assert if to Rotation Matrices are equal.
     *
     * @return Returns True if they are equal, and False otherwise.
     */
    public boolean equals(RotationMatrix compared) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.getElement(i, j) != compared.getElement(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to assert if to Rotation Matrices are equal.
     *
     * @return Returns True if they are equal, and False otherwise.
     */
    @Override
    public boolean equals(Object compared) {
        return compared instanceof RotationMatrix && this.equals((RotationMatrix) compared);
    }


    /**
     * Method to convert a RotationMatrix to a string.
     *
     * @return Returns the RotationMatrix in the form ( a b c ; d e f ; g h i ).
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append("( ");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.append(this.getElement(i, j) + " ");
            }
            if (i == SIZE - 1) {
                result.append(" )");
            } else {
                result.append("; ");
            }
        }

        return result.toString();
    }

    //************ Getters & Setters ************//

    /**
     * Method to get the element i,j of the matrix.
     *
     * @return Returns the element in the ith row and jth column of the matrix.
     */
    public double getElement(int i, int j) {
        if (i < 0 || i > SIZE || j < 0 || j > SIZE) {
            throw new IllegalArgumentException("Attempting to access an inexistent element");
        }

        return matrix[i][j];
    }
}
