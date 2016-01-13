public class Quaternion {
    //fields
    private double r, i, j, k;

    //constructors
    public Quaternion() {}

    public Quaternion(double r, double i, double j, double k) {
        this.r = r;
        this.i = i;
        this.j = j;
        this.k = k;
    }

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
}
