//: object/TestQuaternion.java

import junit.framework.*;

/**
 * JUnit Class to test the methods from the Quaternion Class.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class TestQuaternion extends TestCase {

    /**
     * Constructor
     */
    public TestQuaternion(String name) {
        super(name);
    }

    /**
     * Assert if two Quaternions are equal within a tolerance.
     * 
     * @param expected the expected Quaternion.
     * @param actual the actual Quaternion.
     * @param delta the tolerance.
     */
    public void assertEqualsQuaternion(Quaternion expected, Quaternion actual, double delta){
	assertEquals(expected.getR(),actual.getR(),delta);
	assertEquals(expected.getI(),actual.getI(),delta);
	assertEquals(expected.getJ(),actual.getJ(),delta);
	assertEquals(expected.getK(),actual.getK(),delta);
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

        // Simple tests:
        assertEquals(new Quaternion(1, 2, 3, 4).multiply(new Quaternion(2, 2, 2, 2)),
                new Quaternion(-16, 4, 12, 8));
        assertEquals(new Quaternion(1, 2, 3, 4).multiply(new Quaternion(1, 2, 3, 4)),
                new Quaternion(-28, 4, 6, 8));
        assertEquals(new Quaternion(1, 1, 1, 1).multiply(new Quaternion(0, 0, 0, 0)),
                new Quaternion(0, 0, 0, 0)); // Multiplying by right zero element.
        assertEquals(new Quaternion(0, 0, 0, 0).multiply(new Quaternion(1, 1, 1, 1)),
                new Quaternion(0, 0, 0, 0)); // Multiplying by left zero element.
        assertEqualsQuaternion(new Quaternion(1, 2, 3, 4).multiply(new Quaternion(
                        ((double)1.0)/30, ((double)-2.0)/30, ((double)-3.0)/30, ((double)-4.0)/30)),
                new Quaternion(1, 0, 0, 0),1e-10); // Multiplying by right inverse.
        assertEqualsQuaternion(new Quaternion(
                (double)1.0/30, (double)-2.0/30, (double)-3.0/30, (double)-4.0/30).multiply(new
                Quaternion(1, 2, 3, 4)),new Quaternion(1, 0, 0, 0),1e-10); // Multiplying by left inverse.


        // Negative number tests:
        assertEquals(new Quaternion(-1, -1, -1, -1).multiply(new Quaternion(1, 2, 3, 4)),
                new Quaternion(8, -4, -2, -6));

    }
} ///:~
