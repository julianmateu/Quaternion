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

    //constructors
    public Quaternion() {}

    /** Constructor
     * @param r Real part
     */
    public Quaternion(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

    /** Method to add two Quaternions
     * @return It returns a new Quaternion with the result of the sum.
     */
    public Quaternion add(Quaternion addend) {
        Quaternion result = new Quaternion();
        
        result.r = this.r + addend.r;
        result.i = this.i + addend.i;
        result.j = this.j + addend.j;
        result.k = this.k + addend.k;

        return result;
    }

    public void print() {
        System.out.println("r = " + this.r);
        System.out.println("i = " + this.i);
        System.out.println("j = " + this.j);
        System.out.println("k = " + this.k);
    }
    
    @Override
    public String toString(){
        return "( " + this.r + " ; " + this.i + " ; " + this.j + " ; " + this.k + " )";
    }

    public static void main(String[] args) {
    
        Quaternion a = new Quaternion();

        System.out.println("printing a:");
        a.print();
        System.out.println(a);

        Quaternion b = new Quaternion(1,2,3,4);

        System.out.println("printing b:");
        b.print();
        System.out.println(b);

        Quaternion c = new Quaternion(1,1,1,1);

        System.out.println("printing c:");
        c.print();
        System.out.println(c);

        Quaternion d = b.add(c);

        System.out.println("printing d:");
        d.print();
        System.out.println(d);
    }
} ///:~
