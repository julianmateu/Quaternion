import junit.framework.*;

public class TestQuaternion extends TestCase {

    public TestQuaternion(String name) {
        super(name);
    }

    public void testEquals() {
        Quaternion a = new Quaternion(1,0,-1,5);

        assertEquals(a,new Quaternion(1,0,-1,5));
        assertFalse(a.equals(new Quaternion(-1,0,0,5)));

    }

    public void testAddition() {
        Quaternion a = new Quaternion(1,1,1,-1);
        Quaternion b = new Quaternion(1,0,-1,-1);
        Quaternion result = new Quaternion(2,1,0,-2);

        assertEquals(a.add(b),result);
    }
}
