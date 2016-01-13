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
        
        if (this.r != compared.r) return false;
        if (this.i != compared.i) return false;
        if (this.j != compared.j) return false;
        if (this.k != compared.k) return false;
        else return true;
    }

    @Override
    public String toString(){
        return "( " + this.r + " ; " + this.i + " ; " + this.j + " ; " + this.k + " )";
    }

    public static void main(String[] args) {
    
        Quaternion a = new Quaternion(0,0,0,0);
        System.out.println("a = " + a);

        Quaternion b = new Quaternion(1,2,3,4);
        System.out.println("b = " + b);

        Quaternion c = new Quaternion(1,1,1,1);
        System.out.println("c = " + c);

        Quaternion d = b.add(c);
        System.out.println("d = b + c = " + d );

        System.out.println("d = b + c ? " + d.equals(new Quaternion(2,3,4,5)));
    }
} ///:~
