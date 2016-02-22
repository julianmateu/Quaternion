//: object/TestRotations.java

import junit.framework.*;

/**
 * JUnit Class to test the rotation methods from the Quaternion and RotationMatrix classes.
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class TestRotations extends ProjectTest {

    /**
     * Constructor
     */
    public TestRotations(String name) {
        super(name);
    }

    /**
     * Tester to compare vector rotations using quaternions and rotations matrices.
     */
    public void testRotations() {
        double tol = 10e-6;// Tolerance;

        Vector3D v, axis;
        Quaternion q;
        RotationMatrix m;

        v = new Vector3D(1, 0, 0);
        axis = new Vector3D(1, 1, 1);
        q = new Quaternion(30, axis);
        m = new RotationMatrix(30, axis);
        assertEqualsVector3D(q.rotate(v), m.rotate(v), tol);

        v = new Vector3D(1, 2, -90);
        axis = new Vector3D(-15, 31, 1);
        q = new Quaternion(-123, axis);
        m = new RotationMatrix(-123, axis);
        assertEqualsVector3D(q.rotate(v), m.rotate(v), tol);

        v = new Vector3D(1, 2, 3);
        axis = new Vector3D(1, 2, 3);
        q = new Quaternion(275, axis);
        m = new RotationMatrix(275, axis);
        assertEqualsVector3D(q.rotate(v), m.rotate(v), tol);

        v = new Vector3D(1, -30, 0);
        axis = new Vector3D(-1, 1, 1);
        q = new Quaternion(180, axis);
        m = new RotationMatrix(180, axis);
        assertEqualsVector3D(q.rotate(v), m.rotate(v), tol);
    }
}
