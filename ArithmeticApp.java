import java.util.*;

/**
 * ArithmeticApp.java takes reads lines of input.
 * Passes these two lines to Arithmetic.java.
 * @author Nikolah Pearce.
 */

public class ArithmeticApp {

    public static void main(String[] args) {
        String line1;
        String line2;

        Scanner sc = new Scanner(System.in);
        Arithmetic math = new Arithmetic();

        while (sc.hasNextLine()) {
            line1 = sc.nextLine();
            if (sc.hasNextLine()) {
                line2 = sc.nextLine();
            } else {
                break;
            }
            math.setVariables(line1, line2);
            math.calculate();
        }
            

    }
}
