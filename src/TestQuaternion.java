//: object/TestQuaternion.java
import junit.framework.*;

/** JUnit Class to test the methods from the Quaternion Class.
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class TestQuaternion extends TestCase {

    /** Constructor
     */
    public TestQuaternion(String name) {
        super(name);
    }

    /** Tester for the equals() method.
     */
    public void testEquals() {
        Quaternion a = new Quaternion(1,0,-1,5);

        assertEquals(a,new Quaternion(1,0,-1,5));
        assertEquals(a,a);
        assertFalse(a.equals(new Quaternion(-1,0,0,5)));

    }

    /** Tester for the add() method.
     */
    public void testAddition() {

        // Simple tests:
        assertEquals(new Quaternion(1,2,3,4).add(new Quaternion(1,1,1,1)),new Quaternion(2,3,4,5));
        assertEquals(new Quaternion(1,1,1,1).add(new Quaternion(0,0,0,0)),new Quaternion(1,1,1,1)); // Adding Identity element.

        // Negative number tests:
        assertEquals(new Quaternion(-1,-1,-1,-1).add(new Quaternion(0,0,0,0)),new Quaternion(-1,-1,-1,-1));
        assertEquals(new Quaternion(1,1,1,1).add(new Quaternion(-1,-1,-1,-1)),new Quaternion(0,0,0,0));
        assertEquals(new Quaternion(-1,-1,-1,-1).add(new Quaternion(1,1,1,1)),new Quaternion(0,0,0,0)); // Commutativity
        assertEquals(new Quaternion(-1,-1,-1,-1).add(new Quaternion(-1,-1,-1,-1)),new Quaternion(-2,-2,-2,-2));

        // Mixed numbers tests:
        assertEquals(new Quaternion(1,1,1,-1).add(new Quaternion(1,0,-1,-1)),new Quaternion(2,1,0,-2));
        assertEquals(new Quaternion(-1,4,70,-1).add(new Quaternion(-9,-4,30,4)),new Quaternion(-10,0,100,3));
        
        // Floating point tests:
        assertEquals(new Quaternion(1.25,1e10,1.234e-1,-1).add(new Quaternion(1.25,2e9,-1.234e-1,-1)),new Quaternion(2.5,1.2e10,0,-2));
    }
} ///:~
