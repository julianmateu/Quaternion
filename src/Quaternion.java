//: object/Quaternion.java

/**
 * A Java Class to operate with Quaternions.
 * Read more about Quaternions <a href="https://en.wikipedia.org/wiki/Quaternion">here</a>
 *
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class Quaternion {

    private static final double MIN_TOLERANCE = 1e-10;

    //************ fields ************//
    private double r, i, j, k;

    /**
     * Constructor.
     *
     * @param r Real part.
     */
    public Quaternion(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    /**
     * Constructor from angle and axis.
     *
     * @param angle Angle of counterclockwise rotation in degrees.
     * @param axis  Vector3D that represents the axis of rotation.
     */
    public Quaternion(double angle, Vector3D axis) {
        double c = Math.cos(Math.toRadians(angle) / 2.0);
        double s = Math.sin(Math.toRadians(angle) / 2.0);

        if (axis.norm() != 1) {
            axis = axis.normalize();
        }

        axis = axis.multiply(s);

        this.r = c;
        this.i = axis.x();
        this.j = axis.y();
        this.k = axis.z();
    }

    /**
     * Constructor for basic rotation around X axis.
     *
     * @param angle Angle in degrees of counterclockwise rotation around X axis.
     */
    public static Quaternion basicRotationX(double angle) {
        return new Quaternion(angle, new Vector3D(1, 0, 0));
    }

    /**
     * Constructor for basic rotation around Y axis.
     *
     * @param angle Angle in degrees of counterclockwise rotation around Y axis.
     */
    public static Quaternion basicRotationY(double angle) {
        return new Quaternion(angle, new Vector3D(0, 1, 0));
    }

    /**
     * Constructor for basic rotation around Z axis.
     *
     * @param angle Angle in degrees of counterclockwise rotation around Z axis.
     */
    public static Quaternion basicRotationZ(double angle) {
        return new Quaternion(angle, new Vector3D(0, 0, 1));
    }

    //************ Methods ************//

    /**
     * Method to add two Quaternions.
     *
     * @return It returns a new Quaternion with the result of the sum.
     */
    public Quaternion add(Quaternion addend) {
        return new Quaternion(
                this.getR() + addend.getR(),
                this.getI() + addend.getI(),
                this.getJ() + addend.getJ(),
                this.getK() + addend.getK());
    }

    /**
     * Method to subtract two Quaternions.
     *
     * @return It returns a new Quaternion with the result of the subtraction.
     */
    public Quaternion subtract(Quaternion subtrahend) {
        return new Quaternion(
                this.getR() - subtrahend.getR(),
                this.getI() - subtrahend.getI(),
                this.getJ() - subtrahend.getJ(),
                this.getK() - subtrahend.getK());
    }

    /**
     * Method to multiply two Quaternions and find their Hamilton product.
     *
     * @return Returns a new Quaternion with the result of the Hamilton product.
     */
    public Quaternion multiply(Quaternion factor) {
        return new Quaternion(
                this.getR() * factor.getR() - this.getI() * factor.getI()
                        - this.getJ() * factor.getJ() - this.getK() * factor.getK(),
                this.getR() * factor.getI() + this.getI() * factor.getR()
                        + this.getJ() * factor.getK() - this.getK() * factor.getJ(),
                this.getR() * factor.getJ() - this.getI() * factor.getK()
                        + this.getJ() * factor.getR() + this.getK() * factor.getI(),
                this.getR() * factor.getK() + this.getI() * factor.getJ()
                        - this.getJ() * factor.getI() + this.getK() * factor.getR());
    }

    /**
     * Method to multiply a Quaternion by a scalar.
     *
     * @return Returns a new Quaternion with the result of the product by scalar.
     */
    public Quaternion multiply(double scalar) {
        return new Quaternion(
                this.getR() * scalar,
                this.getI() * scalar,
                this.getJ() * scalar,
                this.getK() * scalar);
    }

    /**
     * Method to conjugate a Quaternion.
     *
     * @return Returns a new Quaternion with the conjugate.
     */
    public Quaternion conjugate() {
        return new Quaternion(
                this.getR(),
                -this.getI(),
                -this.getJ(),
                -this.getK());
    }

    /**
     * Method to conjugate a Quaternion using multiplications only.
     *
     * @return Returns a new Quaternion with the conjugate, but using the formula
     * q* = -(1/2)*(q + iqi + jqj + kqk).
     */
    public Quaternion conjugateMultiplying() {
        Quaternion i = new Quaternion(0, 1, 0, 0);
        Quaternion j = new Quaternion(0, 0, 1, 0);
        Quaternion k = new Quaternion(0, 0, 0, 1);

        Quaternion result = this;
        result = result.add(i.multiply(this.multiply(i)));
        result = result.add(j.multiply(this.multiply(j)));
        result = result.add(k.multiply(this.multiply(k)));
        return result.multiply(-0.5);
    }

    /**
     * Method to calculate the norm of a Quaternion.
     *
     * @return Returns the norm of the quaternion.
     */
    public double norm() {
        return Math.sqrt(this.getR() * this.getR() +
                this.getI() * this.getI() +
                this.getJ() * this.getJ() +
                this.getK() * this.getK());
    }

    /**
     * Method to normalize a Quaternion.
     *
     * @return Returns a new Quaternion with unit norm, un the same direction.
     */
    public Quaternion normalize() {

        double norm = this.norm();

        if (norm < MIN_TOLERANCE) {
            throw new IllegalArgumentException("Trying to normalize a zero norm quaternion");
        }

        return new Quaternion(
                this.getR(),
                this.getI(),
                this.getJ(),
                this.getK()).multiply(1.0 / norm);
    }

    /**
     * Method to rotate a Vector3D using quaternions.
     *
     * @param vector Vector3D to rotate.
     * @return Result of the rotation.
     */
    public Vector3D rotate(Vector3D vector) {
        Quaternion p = new Quaternion(0, vector.x(), vector.y(), vector.z());
        Quaternion q = this.normalize();
        Quaternion result = q.multiply(p.multiply(q.conjugate()));

        return new Vector3D(result.getI(), result.getJ(), result.getK());
    }


    /**
     * Method to assert if to Quaternions are equal.
     *
     * @return Returns True if they are equal, and False otherwise.
     */
    public boolean equals(Quaternion compared) {

        return this.getR() == compared.getR() && this.getI() == compared.getI() && this.getJ() ==
                compared.getJ() && this.getK() == compared.getK();
    }

    /**
     * Method to assert if to Quaternions are equal.
     *
     * @return Returns True if they are equal, and False otherwise.
     */
    @Override
    public boolean equals(Object compared) {
        return compared instanceof Quaternion && this.equals((Quaternion) compared);
    }

    /**
     * Method to convert a Quaternion to a string.
     *
     * @return Returns the Quaternion in the form ( r ; i ; j ; k ).
     */
    @Override
    public String toString() {
        return "( " + this.getR() + " ; " + this.getI() + " ; " +
                this.getJ() + " ; " + this.getK() + " )";
    }

    //************ Getters & Setters ************//

    /**
     * r Getter.
     */
    public double getR() {
        return this.r;
    }

    /**
     * i Getter.
     */
    public double getI() {
        return this.i;
    }


    /**
     * j Getter.
     */
    public double getJ() {
        return this.j;
    }

    /**
     * k Getter.
     */
    public double getK() {
        return this.k;
    }

    /**
     * r Setter.
     */
    private void setR(double r) {
        this.r = r;
    }

    /**
     * i Setter.
     */
    private void setI(double i) {
        this.i = i;
    }

    /**
     * j Setter.
     */
    private void setJ(double j) {
        this.j = j;
    }

    /**
     * k Setter.
     */
    private void setK(double k) {
        this.k = k;
    }
} ///:~
