import junit.framework.*;
/**
 * Project-wide base class for Testing.
 */
public class ProjectTest extends TestCase {

    /**
     * Constructor.
     */
    public ProjectTest(String name) {
        super(name);
    }

    /**
     * Assert if two Quaternions are equal within a tolerance.
     *
     * @param expected the expected Quaternion.
     * @param actual   the actual Quaternion.
     * @param delta    the tolerance.
     */
    public void assertEqualsQuaternion(Quaternion expected, Quaternion actual, double delta) {
        assertEquals(expected.getR(), actual.getR(), delta);
        assertEquals(expected.getI(), actual.getI(), delta);
        assertEquals(expected.getJ(), actual.getJ(), delta);
        assertEquals(expected.getK(), actual.getK(), delta);
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
}
