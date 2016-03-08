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
public class TestRotationMatrix extends ProjectTest {

    private static final int SIZE = 3;

    /**
     * Constructor
     */
    public TestRotationMatrix(String name) {
        super(name);
    }

    public void testEquals() {
        Vector3D axis = new Vector3D(1, 0, 0);
        RotationMatrix a = new RotationMatrix(90, axis);

        assertEquals(a, new RotationMatrix(90, axis));
        assertEquals(a, a);
        assertFalse(a.equals(new RotationMatrix(45, axis)));

    }

    public void testGetElement() {

        RotationMatrix m = new RotationMatrix(new double[][]{{0, 1, 2}, {1, 2, 3}, {2, 3, 4}});

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                assertEquals((double) i + j, m.getElement(i, j));
            }
        }

        try {
            m.getElement(0, 3);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.getElement(43, 2);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.getElement(-10, 0);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            m.getElement(-1, 2134);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }


    }

    /**
     * Tester for the constructors.
     */
    public void testConstructors() {
        double tolerance = 1e-10;

        // Rotation about Z axis.
        Vector3D axis = new Vector3D(0, 0, 1);
        double[][] matrix = new double[][]{{0, -1, 0}, {1, 0, 0}, {0, 0, 1}};
        assertEqualsRotationMatrix(new RotationMatrix(90, axis),
                new RotationMatrix(matrix), tolerance);
        assertEqualsRotationMatrix(RotationMatrix.basicRotationZ(90),
                new RotationMatrix(matrix), tolerance);

        // Rotation about Y axis.
        axis = new Vector3D(0, 1, 0);
        matrix = new double[][]{{0, 0, 1}, {0, 1, 0}, {-1, 0, 0}};
        assertEqualsRotationMatrix(new RotationMatrix(90, axis),
                new RotationMatrix(matrix), tolerance);
        assertEqualsRotationMatrix(RotationMatrix.basicRotationY(90),
                new RotationMatrix(matrix), tolerance);

        // Rotation about X axis.
        axis = new Vector3D(1, 0, 0);
        matrix = new double[][]{{1, 0, 0}, {0, 0, -1}, {0, 1, 0}};
        assertEqualsRotationMatrix(new RotationMatrix(90, axis),
                new RotationMatrix(matrix), tolerance);
        assertEqualsRotationMatrix(RotationMatrix.basicRotationX(90),
                new RotationMatrix(matrix), tolerance);

        try {
            new RotationMatrix(90, null);
            fail("Should have thrown a NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    /**
     * Tester for the rotation method.
     */
    public void testRotation() throws ZeroNormException {

        double tolerance = 10e-6;
        Vector3D expected, vector;

        expected = new Vector3D(1, 2, 3);
        vector = new Vector3D(1, 2, 3);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationX(0).rotate(vector), tolerance);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationY(0).rotate(vector), tolerance);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationZ(0).rotate(vector), tolerance);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationX(360).rotate(vector),
                tolerance);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationX(360).rotate(vector),
                tolerance);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationX(360).rotate(vector),
                tolerance);

        vector = new Vector3D(1, 0, 0);
        expected = new Vector3D(0, 1, 0);
        assertEqualsVector3D(expected, RotationMatrix.basicRotationZ(90).rotate(vector), tolerance);

        expected = new Vector3D(1, 1, 0);
        expected = expected.normalize();
        assertEqualsVector3D(expected, RotationMatrix.basicRotationZ(45).rotate(vector), tolerance);

        expected = new Vector3D(1, -1, 0);
        expected = expected.normalize();
        assertEqualsVector3D(expected, RotationMatrix.basicRotationZ(-45).rotate(vector),
                tolerance);

    }

} ///:~
