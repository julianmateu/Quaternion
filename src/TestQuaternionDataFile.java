import junit.framework.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TestQuaternionDataFile extends TestCase {

    private final static int QUATERNION_SIZE = 4;
    
    public TestQuaternionDataFile(String name) {
        super(name);
    }
    /* Run all the tests in testdata.txt (does not test
     * exeption case). We'll get an error if any of the 
     * file I/O goes wrong.
     */
    private Quaternion tokenize_quaternion(StringTokenizer st) {

        double[]  aux = new double[QUATERNION_SIZE];
        for (int i=0; i<QUATERNION_SIZE; i++) {
            String val = st.nextToken();
            aux[i] = Double.parseDouble(val);
        }
            
        return new Quaternion(aux[0],aux[1],aux[2],aux[3]);
    }

    public void testFromFile() throws Exception {


        String line;
        BufferedReader rdr = new BufferedReader(new FileReader("testdata.txt"));

        while ((line = rdr.readLine()) != null) {
            
            if (line.startsWith("#")) { // Ignore comments
                continue;
            }
            
            StringTokenizer st = new StringTokenizer(line);
            
            if (!st.hasMoreTokens()) {
                continue; // Blank line
            }

            // Get the expected result value
            Quaternion expected = tokenize_quaternion(st);

            //Get the first addend
            Quaternion first = tokenize_quaternion(st);

            //Get the second addend
            Quaternion second = tokenize_quaternion(st);

            // And run the assert
            assertEquals(expected,first.add(second));
        }
    }
}
