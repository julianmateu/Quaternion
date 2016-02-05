//: object/Quaternion.java

/** A Java Class to operate with Quaternions.
 * Read more about Quaternions <a href="https://en.wikipedia.org/wiki/Quaternion">here</a>
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class Quaternion {
    //************ fields ************//
    private double r, i, j, k;

    /** Constructor
     * @param r Real part
     */
    public Quaternion(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    //************ Methods ************//

    /** Method to add two Quaternions
     * @return It returns a new Quaternion with the result of the sum.
     */
    public Quaternion add(Quaternion addend) {
        return new Quaternion(
            this.r + addend.getR(),
            this.i + addend.getI(),
            this.j + addend.getJ(),
            this.k + addend.getK());
    }

    /** Method to subtract two Quaternions
     * @return It returns a new Quaternion with the result of the subtraction.
     */
    public Quaternion subtract(Quaternion subtrahend) {
        return new Quaternion(
                this.r - subtrahend.getR(),
                this.i - subtrahend.getI(),
                this.j - subtrahend.getJ(),
                this.k - subtrahend.getK());
    }

    /** Method to multiply two Quaternions and find their Hamilton product.
     * @return Returns a new Quaternion with the result of the Hamilton product.
     */
    public Quaternion multiply(Quaternion factor) {
        return new Quaternion(
                this.r * factor.getR() - this.i * factor.getI()
                - this.j * factor.getJ() - this.k * factor.getK(),
                this.r * factor.getI() + this.i * factor.getR()
                + this.j * factor.getK() - this.k * factor.getJ(),
                this.r * factor.getJ() - this.i * factor.getK()
                + this.j * factor.getR() + this.k * factor.getI(),
                this.r * factor.getK() + this.i * factor.getJ()
                - this.j * factor.getI() + this.k * factor.getR());
    }

    /** Method to assert if to Quaternions are equal.
     * @return Returns True if they are equal, and False otherwise.
     */
    public boolean equals(Quaternion compared) {
        
        return this.r == compared.getR() && this.i == compared.getI() && this.j == compared.getJ() && this.k == compared.getK();
    }

    /** Method to assert if to Quaternions are equal.
     * @return Returns True if they are equal, and False otherwise.
     */
    @Override
    public boolean equals(Object compared) {
        return compared instanceof Quaternion && this.equals((Quaternion) compared);
    }

    /** Method to convert a Quaternion to a string.
     * @return Returns the Quaternion in the form ( r ; i ; j ; k ).
     */
    @Override
    public String toString(){
        return "( " + this.r + " ; " + this.i + " ; " + this.j + " ; " + this.k + " )";
    }
 
    //************ Getters & Setters ************//
    
    /** r Getter
     */
    public double getR() {
        return this.r;
    }

    /** i Getter
     */
    public double getI() {
        return this.i;
    }


    /** j Getter
     */
    public double getJ() {
        return this.j;
    }

    /** k Getter
     */
    public double getK() {
        return this.k;
    }

    /** r Setter
     */
    public void setR(double r) {
        this.r = r;
    }

    /** i Setter
     */
    public void setI(double i) {
        this.i = i;
    }

    /** j Setter
     */
    public void setJ(double j) {
        this.j = j;
    }

    /** k Setter
     */
    public void setK(double k) {
        this.k = k;
    }
} ///:~
