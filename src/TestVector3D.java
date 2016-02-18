//: object/TestVector3D.java

import junit.framework.*;

/**
 * JUnit Class to test the methods from the Vector3D Class.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class TestVector3D extends TestCase {

    /**
     * Constructor
     */
    public TestVector3D(String name) {
        super(name);
    }

    /**
     * Assert if two Vector3Ds are equal within a tolerance.
     *
     * @param expected the expected Vector3D.
     * @param actual   the actual Vector3D.
     * @param delta    the tolerance.
     */
    public void assertEqualsVector3D(Vector3D expected, Vector3D actual, double delta) {
        assertEquals(expected.x(), actual.x(), delta);
        assertEquals(expected.y(), actual.y(), delta);
        assertEquals(expected.z(), actual.z(), delta);
    }

    /**
     * Tester for the equals() method.
     */
    public void testEquals() {
        Vector3D a = new Vector3D(1, 0, -1);

        assertEquals(a, new Vector3D(1, 0, -1));
        assertEquals(a, a);
        assertFalse(a.equals(new Vector3D(-1, 0, 8)));

    }

    /**
     * Tester for the add() method.
     */
    public void testAddition() {

        // Simple tests:
        assertEquals(new Vector3D(1, 2, 3).add(new Vector3D(1, 1, 1)),
                new Vector3D(2, 3, 4));
        assertEquals(new Vector3D(1, 1, 1).add(new Vector3D(0, 0, 0)),
                new Vector3D(1, 1, 1)); // Adding Identity element.

        // Negative number tests:
        assertEquals(new Vector3D(-1, -1, -1).add(new Vector3D(0, 0, 0)),
                new Vector3D(-1, -1, -1));
        assertEquals(new Vector3D(1, 1, 1).add(new Vector3D(-1, -1, -1)),
                new Vector3D(0, 0, 0)); // Adding additive inverse.
        assertEquals(new Vector3D(-1, -1, -1).add(new Vector3D(1, 1, 1)),
                new Vector3D(1, 1, 1).add(new Vector3D(-1, -1, -1))); // Commutativity.
        assertEquals(new Vector3D(-1, -1, -1).add(new Vector3D(-1, -1, -1)),
                new Vector3D(-2, -2, -2));

        // Mixed numbers tests:
        assertEquals(new Vector3D(1, 1, -1).add(new Vector3D(1, 0, -1)),
                new Vector3D(2, 1, -2));
        assertEquals(new Vector3D(-1, 4, 70).add(new Vector3D(-9, -4, 30)),
                new Vector3D(-10, 0, 100));

        // Floating point tests:
        assertEquals(new Vector3D(1.25, 1e10, 1.234e-1).add(
                new Vector3D(1.25, 2e9, -1.234e-1)), new Vector3D(2.5, 1.2e10, 0));
    }


    /**
     * Tester for the subtract() method.
     */
    public void testSubtraction() {

        // Simple tests:
        assertEquals(new Vector3D(1, 2, 3).subtract(new Vector3D(1, 1, 1)),
                new Vector3D(0, 1, 2));
        assertEquals(new Vector3D(1, 1, 1).subtract(new Vector3D(0, 0, 0)),
                new Vector3D(1, 1, 1)); // Subtracting Identity element.
        assertEquals(new Vector3D(1, 2, 3).subtract(new Vector3D(1, 2, 3)),
                new Vector3D(0, 0, 0));

        // Negative number tests:
        assertEquals(new Vector3D(0, 0, 0).subtract(new Vector3D(1, 1, 1)),
                new Vector3D(-1, -1, -1)); // Inverse.
        assertEquals(new Vector3D(-1, -1, -1).subtract(new Vector3D(-1, -1, -1)),
                new Vector3D(0, 0, 0));
        assertEquals(new Vector3D(1, 1, 1).subtract(new Vector3D(-1, -1, -1)),
                new Vector3D(2, 2, 2));
        assertEquals(new Vector3D(-1, -1, -1).subtract(new Vector3D(1, 1, 1)),
                new Vector3D(-2, -2, -2)); // Anticommutativity.

        // Mixed numbers tests:
        assertEquals(new Vector3D(1, 1, 1).subtract(new Vector3D(1, 0, -1)),
                new Vector3D(0, 1, 2));
        assertEquals(new Vector3D(-1, 4, 70).subtract(new Vector3D(9, 4, -30)),
                new Vector3D(-10, 0, 100));

        // Floating point tests:
        assertEquals(new Vector3D(1.25, 1e10, 1.234e-1).subtract(
                new Vector3D(-1.25, -2e9, 1.234e-1)), new Vector3D(2.5, 1.2e10, 0));
    }

    /**
     * Tester for the multiply(double) method.
     */
    public void testMultiplicationDouble() {

        Vector3D expected;
        Vector3D actual;

        // Simple tests:
        assertEquals(new Vector3D(1, 2, 3).multiply(2),
                new Vector3D(2, 4, 6));
        assertEquals(new Vector3D(1, 2, 3).multiply(1),
                new Vector3D(1, 2, 3)); // Multiplying by identity element.
        assertEquals(new Vector3D(1, 1, 1).multiply(4.0),
                new Vector3D(4, 4, 4));
        assertEquals(new Vector3D(1, 1, 1).multiply(0.0),
                new Vector3D(0, 0, 0)); // Multiplying by zero scalar.
        assertEquals(new Vector3D(0, 0, 0).multiply(1.0),
                new Vector3D(0, 0, 0)); // Multiplying by zero Vector3D.
        assertEquals(new Vector3D(2, 2, 2).multiply(0.5),
                new Vector3D(1, 1, 1)); // Multiplying by inverse of each element.


        // Negative number tests:
        assertEquals(new Vector3D(-1, -1, -1).multiply(1),
                new Vector3D(-1, -1, -1));
        assertEquals(new Vector3D(1, 1, 1).multiply(-1),
                new Vector3D(-1, -1, -1));
        assertEquals(new Vector3D(-1, -1, -1).multiply(-1),
                new Vector3D(1, 1, 1));


        // Mixed numbers tests:
        assertEquals(new Vector3D(-1, 2, 3).multiply(2),
                new Vector3D(-2, 4, 6));
        assertEquals(new Vector3D(-1, 4, 70).multiply(-3),
                new Vector3D(3, -12, -210));

        // Floating point tests:
        assertEquals(new Vector3D(1e1, 1e10, 1.234e-1).multiply(1e10),
                new Vector3D(1e11, 1e20, 1.234e9));
        assertEquals(new Vector3D(1e1, 1e10, 1.234e-1).multiply(-1e10),
                new Vector3D(-1e11, -1e20, -1.234e9));
    }

    /**
     * Tester for the norm method.
     */
    public void testNorm() {

        double tol = 1e-6; // Tolerance.
        assertEquals(new Vector3D(1, 1, 1).norm(), Math.sqrt(3), tol);
        assertEquals(new Vector3D(1, 0, 0).norm(), 1, tol);
        assertEquals(new Vector3D(-1, 1, -1).norm(), Math.sqrt(3), tol);
        assertEquals(new Vector3D(1, 2, -3).norm(), Math.sqrt(14), tol);
        assertEquals(new Vector3D(3, 4, 12).norm(), 13, tol);

        try {
            Vector3D vector = new Vector3D(0, 0, 0);
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
        assertEquals(new Vector3D(1, 0, 0).normalize(), new Vector3D(1, 0, 0));
        assertEquals(new Vector3D(1, -1, 0).normalize(),
                new Vector3D(1.0 / Math.sqrt(2), -1.0 / Math.sqrt(2), 0));
        assertEquals(new Vector3D(1, 1, 1).normalize(),
                new Vector3D(1, 1, 1).multiply(1.0 / Math.sqrt(3)));
        assertEquals(new Vector3D(-1, 0, 0).normalize(), new Vector3D(-1, 0, 0));
    }
} ///:~
