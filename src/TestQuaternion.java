//: object/TestQuaternion.java

import junit.framework.*;

/**
 * JUnit Class to test the methods from the Quaternion Class.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class TestQuaternion extends ProjectTest {

    /**
     * Constructor
     */
    public TestQuaternion(String name) {
        super(name);
    }

    /**
     * Tester for the equals() method.
     */
    public void testEquals() {
        Quaternion a = new Quaternion(1, 0, -1, 5);

        assertEquals(a, new Quaternion(1, 0, -1, 5));
        assertEquals(a, a);
        assertFalse(a.equals(new Quaternion(-1, 0, 0, 5)));
    }

    /**
     * Tester for the constructors.
     */
    public void testConstructors() {
        double tol = 1e-10; // Tolerance.

        // Rotation about Z axis.
        Vector3D axis = new Vector3D(0, 0, 1);
        Quaternion rotation = new Quaternion(1, 0, 0, 1).multiply(1 / Math.sqrt(2));
        assertEqualsQuaternion(new Quaternion(90, axis), rotation, tol);
        assertEqualsQuaternion(Quaternion.basicRotationZ(90), rotation, tol);

        // Rotation about Y axis.
        axis = new Vector3D(0, 1, 0);
        rotation = new Quaternion(1, 0, 1, 0).multiply(1 / Math.sqrt(2));
        assertEqualsQuaternion(new Quaternion(90, axis), rotation, tol);
        assertEqualsQuaternion(Quaternion.basicRotationY(90), rotation, tol);

        // Rotation about X axis.
        axis = new Vector3D(1, 0, 0);
        rotation = new Quaternion(1, 1, 0, 0).multiply(1 / Math.sqrt(2));
        assertEqualsQuaternion(new Quaternion(90, axis), rotation, tol);
        assertEqualsQuaternion(Quaternion.basicRotationX(90), rotation, tol);
    }

    /**
     * Tester for the add() method.
     */
    public void testAddition() {

        // Simple tests:
        assertEquals(new Quaternion(1, 2, 3, 4).add(new Quaternion(1, 1, 1, 1)),
                new Quaternion(2, 3, 4, 5));
        assertEquals(new Quaternion(1, 1, 1, 1).add(new Quaternion(0, 0, 0, 0)),
                new Quaternion(1, 1, 1, 1)); // Adding Identity element.

        // Negative number tests:
        assertEquals(new Quaternion(-1, -1, -1, -1).add(new Quaternion(0, 0, 0, 0)),
                new Quaternion(-1, -1, -1, -1));
        assertEquals(new Quaternion(1, 1, 1, 1).add(new Quaternion(-1, -1, -1, -1)),
                new Quaternion(0, 0, 0, 0)); // Adding additive inverse.
        assertEquals(new Quaternion(-1, -1, -1, -1).add(new Quaternion(1, 1, 1, 1)),
                new Quaternion(1, 1, 1, 1).add(new Quaternion(-1, -1, -1, -1))); // Commutativity.
        assertEquals(new Quaternion(-1, -1, -1, -1).add(new Quaternion(-1, -1, -1, -1)),
                new Quaternion(-2, -2, -2, -2));

        // Mixed numbers tests:
        assertEquals(new Quaternion(1, 1, 1, -1).add(new Quaternion(1, 0, -1, -1)),
                new Quaternion(2, 1, 0, -2));
        assertEquals(new Quaternion(-1, 4, 70, -1).add(new Quaternion(-9, -4, 30, 4)),
                new Quaternion(-10, 0, 100, 3));

        // Floating point tests:
        assertEquals(new Quaternion(1.25, 1e10, 1.234e-1, -1).add(
                new Quaternion(1.25, 2e9, -1.234e-1, -1)), new Quaternion(2.5, 1.2e10, 0, -2));
    }


    /**
     * Tester for the subtract() method.
     */
    public void testSubtraction() {

        // Simple tests:
        assertEquals(new Quaternion(1, 2, 3, 4).subtract(new Quaternion(1, 1, 1, 1)),
                new Quaternion(0, 1, 2, 3));
        assertEquals(new Quaternion(1, 1, 1, 1).subtract(new Quaternion(0, 0, 0, 0)),
                new Quaternion(1, 1, 1, 1)); // Subtracting Identity element.
        assertEquals(new Quaternion(1, 2, 3, 4).subtract(new Quaternion(1, 2, 3, 4)),
                new Quaternion(0, 0, 0, 0));

        // Negative number tests:
        assertEquals(new Quaternion(0, 0, 0, 0).subtract(new Quaternion(1, 1, 1, 1)),
                new Quaternion(-1, -1, -1, -1)); // Inverse.
        assertEquals(new Quaternion(-1, -1, -1, -1).subtract(new Quaternion(-1, -1, -1, -1)),
                new Quaternion(0, 0, 0, 0));
        assertEquals(new Quaternion(1, 1, 1, 1).subtract(new Quaternion(-1, -1, -1, -1)),
                new Quaternion(2, 2, 2, 2));
        assertEquals(new Quaternion(-1, -1, -1, -1).subtract(new Quaternion(1, 1, 1, 1)),
                new Quaternion(-2, -2, -2, -2)); // Anticommutativity.

        // Mixed numbers tests:
        assertEquals(new Quaternion(1, 1, 1, -1).subtract(new Quaternion(1, 0, -1, 1)),
                new Quaternion(0, 1, 2, -2));
        assertEquals(new Quaternion(-1, 4, 70, -1).subtract(new Quaternion(9, 4, -30, -4)),
                new Quaternion(-10, 0, 100, 3));

        // Floating point tests:
        assertEquals(new Quaternion(1.25, 1e10, 1.234e-1, -1).subtract(
                new Quaternion(-1.25, -2e9, 1.234e-1, 1)), new Quaternion(2.5, 1.2e10, 0, -2));
    }

    /**
     * Tester for the multiply(double) method.
     */
    public void testMultiplicationDouble() {

        Quaternion expected;
        Quaternion actual;

        // Simple tests:
        assertEquals(new Quaternion(1, 2, 3, 4).multiply(2),
                new Quaternion(2, 4, 6, 8));
        assertEquals(new Quaternion(1, 2, 3, 4).multiply(1),
                new Quaternion(1, 2, 3, 4)); // Multiplying by identity element.
        assertEquals(new Quaternion(1, 1, 1, 1).multiply(4.0),
                new Quaternion(4, 4, 4, 4));
        assertEquals(new Quaternion(1, 1, 1, 1).multiply(0.0),
                new Quaternion(0, 0, 0, 0)); // Multiplying by zero scalar.
        assertEquals(new Quaternion(0, 0, 0, 0).multiply(1.0),
                new Quaternion(0, 0, 0, 0)); // Multiplying by zero quaternion.
        assertEquals(new Quaternion(2, 2, 2, 2).multiply(0.5),
                new Quaternion(1, 1, 1, 1)); // Multiplying by inverse of each element.


        // Negative number tests:
        assertEquals(new Quaternion(-1, -1, -1, -1).multiply(1),
                new Quaternion(-1, -1, -1, -1));
        assertEquals(new Quaternion(1, 1, 1, 1).multiply(-1),
                new Quaternion(-1, -1, -1, -1));
        assertEquals(new Quaternion(-1, -1, -1, -1).multiply(-1),
                new Quaternion(1, 1, 1, 1));


        // Mixed numbers tests:
        assertEquals(new Quaternion(-1, 2, 3, -4).multiply(2),
                new Quaternion(-2, 4, 6, -8));
        assertEquals(new Quaternion(-1, 4, 70, -4).multiply(-3),
                new Quaternion(3, -12, -210, 12));

        // Floating point tests:
        assertEquals(new Quaternion(1e1, 1e10, 1.234e-1, -1).multiply(1e10),
                new Quaternion(1e11, 1e20, 1.234e9, -1e10));
        assertEquals(new Quaternion(1e1, 1e10, 1.234e-1, -1).multiply(-1e10),
                new Quaternion(-1e11, -1e20, -1.234e9, 1e10));
    }

    /**
     * Tester for the multiply(Quaternion) method.
     */
    public void testMultiplicationQuaternion() {

        Quaternion expected;
        Quaternion actual;

        // Simple tests:
        expected = new Quaternion(1, 2, 3, 4).multiply(new Quaternion(2, 2, 2, 2));
        assertEquals(expected, new Quaternion(-16, 4, 12, 8));

        expected = new Quaternion(1, 2, 3, 4).multiply(new Quaternion(1, 2, 3, 4));
        assertEquals(expected, new Quaternion(-28, 4, 6, 8));

        expected = new Quaternion(1, 1, 1, 1).multiply(new Quaternion(0, 0, 0, 0));
        assertEquals(expected, new Quaternion(0, 0, 0, 0)); // Multiplying by right zero element.

        expected = new Quaternion(0, 0, 0, 0).multiply(new Quaternion(1, 1, 1, 1));
        assertEquals(expected, new Quaternion(0, 0, 0, 0)); // Multiplying by left zero element.

        expected = new Quaternion(1, 2, 3, 4).multiply(
                new Quaternion(1, -2, -3, -4).multiply(1.0 / 30));
        actual = new Quaternion(1, 0, 0, 0);
        assertEqualsQuaternion(expected, actual, 1e-10);// Multiplying by right inverse.

        expected = new Quaternion(1, -2, -3, -4).multiply(1.0 / 30).multiply(
                new Quaternion(1, 2, 3, 4));
        actual = new Quaternion(1, 0, 0, 0);
        assertEqualsQuaternion(expected, actual, 1e-10); // Multiplying by left inverse.

        // Negative number tests
        expected = new Quaternion(-1, -1, -1, -1).multiply(new Quaternion(1, 2, 3, 4));
        assertEquals(expected, new Quaternion(8, -4, -2, -6));

    }

    /**
     * Tester for the conjugation method.
     */
    public void testConjugation() {
        assertEquals(new Quaternion(1, -1, -1, -1), new Quaternion(1, 1, 1, 1).conjugate());
        assertEquals(new Quaternion(1, 1, 1, 1), new Quaternion(1, -1, -1, -1).conjugate());
        assertEquals(new Quaternion(1, 1, 1, 1), new Quaternion(1, -1, -1, -1).conjugate());

    }

    /**
     * Tester for the conjugateMultiplying method.
     */
    public void testConjugateMultiplying() {
        assertEquals(new Quaternion(1, -1, -1, -1),
                new Quaternion(1, 1, 1, 1).conjugateMultiplying());
        assertEquals(new Quaternion(1, 1, 1, 1),
                new Quaternion(1, -1, -1, -1).conjugateMultiplying());
        assertEquals(new Quaternion(1, 1, 1, 1),
                new Quaternion(1, -1, -1, -1).conjugateMultiplying());

        // Tests for equality between the two forms of conjugation.
        assertEquals(new Quaternion(1, 2, 3, 4).conjugate(),
                new Quaternion(1, 2, 3, 4).conjugateMultiplying());

    }

    /**
     * Tester for the norm method.
     */
    public void testNorm() {

        double tol = 1e-6; // Tolerance.
        assertEquals(new Quaternion(1, 1, 1, 0).norm(), Math.sqrt(3), tol);
        assertEquals(new Quaternion(1, 0, 0, 0).norm(), 1, tol);
        assertEquals(new Quaternion(-1, 1, -1, 1).norm(), 2, tol);
        assertEquals(new Quaternion(1, 2, -3, 4).norm(), Math.sqrt(30), tol);
        assertEquals(new Quaternion(2, 2, 2, 2).norm(), 4, tol);

        try {
            Quaternion vector = new Quaternion(0, 0, 0, 0);
            vector.normalize();
            fail("Should have thrown an IllegalArgumentException for zero norm");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * Tester for the normalize method.
     */
    public void testNormalize() {
        assertEquals(new Quaternion(1, 0, 0, 0).normalize(), new Quaternion(1, 0, 0, 0));
        assertEquals(new Quaternion(1, -1, 0, 0).normalize(),
                new Quaternion(1.0 / Math.sqrt(2), -1.0 / Math.sqrt(2), 0, 0));
        assertEquals(new Quaternion(1, 1, 1, 1).normalize(),
                new Quaternion(1, 1, 1, 1).multiply(1.0 / 2));
        assertEquals(new Quaternion(-1, 0, 0, 0).normalize(), new Quaternion(-1, 0, 0, 0));
    }

    /**
     * Tester for the rotation method.
     */
    public void testRotation() {

        double tol = 10e-6; // Tolerance.
        Vector3D expected, vector;

        expected = new Vector3D(1, 2, 3);
        vector = new Vector3D(1, 2, 3);
        assertEqualsVector3D(expected, Quaternion.basicRotationX(0).rotate(vector), tol);
        assertEqualsVector3D(expected, Quaternion.basicRotationY(0).rotate(vector), tol);
        assertEqualsVector3D(expected, Quaternion.basicRotationZ(0).rotate(vector), tol);
        assertEqualsVector3D(expected, Quaternion.basicRotationX(360).rotate(vector), tol);
        assertEqualsVector3D(expected, Quaternion.basicRotationX(360).rotate(vector), tol);
        assertEqualsVector3D(expected, Quaternion.basicRotationX(360).rotate(vector), tol);

        vector = new Vector3D(1, 0, 0);
        expected = new Vector3D(0, 1, 0);
        assertEqualsVector3D(expected, Quaternion.basicRotationZ(90).rotate(vector), tol);

        expected = new Vector3D(1, 1, 0);
        expected = expected.normalize();
        assertEqualsVector3D(expected, Quaternion.basicRotationZ(45).rotate(vector), tol);

        expected = new Vector3D(1, -1, 0);
        expected = expected.normalize();
        assertEqualsVector3D(expected, Quaternion.basicRotationZ(-45).rotate(vector), tol);

    }
} ///:~
