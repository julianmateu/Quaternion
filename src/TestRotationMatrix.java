//: object/TestRotationMatrix.java

import junit.framework.*;

import java.lang.*;

/**
 * JUnit Class to test the methods from the RotationMatrix Class.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class TestRotationMatrix extends TestCase {

    /**
     * Constructor
     */
    public TestRotationMatrix(String name) {
        super(name);
    }

    /**
     * Assert if two RotationMatrices are equal within a tolerance.
     *
     * @param expected the expected RotationMatrix.
     * @param actual   the actual RotationMatrix.
     * @param delta    the tolerance.
     */
    public void assertEqualsRotationMatrix(RotationMatrix expected,
                                           RotationMatrix actual, double delta) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected.getElement(i, j), actual.getElement(i, j), delta);
            }
        }
    }

    /**
     * Tester for the equals() method.
     */
    public void testEquals() {
        Vector3D axis = new Vector3D(1, 0, 0);
        RotationMatrix a = new RotationMatrix(90, axis);

        assertEquals(a, new RotationMatrix(90, axis));
        assertEquals(a, a);
        assertFalse(a.equals(new RotationMatrix(45, axis)));

    }

    /**
     * Tester for the constructors.
     */
    public void testConstructors() {
        double tol = 1e-10; // Tolerance.

        // Rotation about Z axis.
        Vector3D axis = new Vector3D(0, 0, 1);
        double[][] matrix = new double[][]{{0, -1, 0}, {1, 0, 0}, {0, 0, 1}};
        assertEqualsRotationMatrix(new RotationMatrix(90, axis), new RotationMatrix(matrix), tol);
        assertEqualsRotationMatrix(RotationMatrix.basicRotationZ(90),
                new RotationMatrix(matrix), tol);

        // Rotation about Y axis.
        axis = new Vector3D(0, 1, 0);
        matrix = new double[][]{{0, 0, 1}, {0, 1, 0}, {-1, 0, 0}};
        assertEqualsRotationMatrix(new RotationMatrix(90, axis), new RotationMatrix(matrix), tol);
        assertEqualsRotationMatrix(RotationMatrix.basicRotationY(90),
                new RotationMatrix(matrix), tol);

        // Rotation about X axis.
        axis = new Vector3D(1, 0, 0);
        matrix = new double[][]{{1, 0, 0}, {0, 0, -1}, {0, 1, 0}};
        assertEqualsRotationMatrix(new RotationMatrix(90, axis), new RotationMatrix(matrix), tol);
        assertEqualsRotationMatrix(RotationMatrix.basicRotationX(90),
                new RotationMatrix(matrix), tol);

        try {
            new RotationMatrix(90, null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
} ///:~
