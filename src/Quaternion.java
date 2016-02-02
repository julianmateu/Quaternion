//: object/Quaternion.java

/** A Java Class to operate with Quaternions.
 * Read more about Quaternions <a href="https://en.wikipedia.org/wiki/Quaternion">here</a>
 * @author Julian Mateu
 * @author julianmateu@gmail.com
 * @version 1.0
 */
public class Quaternion {
    //fields
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

    // Getters & Setters
    
    /** r Getter
     */
    public double getR() {return this.r;}

    /** i Getter
     */
    public double getI() {return this.i;}


    /** j Getter
     */
    public double getJ() {return this.j;}

    /** k Getter
     */
    public double getK() {return this.k;}

    /** r Setter
     */
    public void setR(double r) {this.r = r;}

    /** i Setter
     */
    public void setI(double i) {this.i = i;}

    /** j Setter
     */
    public void setJ(double j) {this.j = j;}

    /** k Setter
     */
    public void setK(double k) {this.k = k;}

    // Methods

    /** Method to add two Quaternions
     * @return It returns a new Quaternion with the result of the sum.
     */
    public Quaternion add(Quaternion addend) {
        return new Quaternion(
            this.r + addend.r,
            this.i + addend.i,
            this.j + addend.j,
            this.k + addend.k);
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
} ///:~
