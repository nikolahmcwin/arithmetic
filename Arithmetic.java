import java.util.ArrayList;

/**
 * Arithmetic.java takes 2 lines of input.
 * If the first line can use operations to make the second, 
 * it will find and print these operations.
 * If no correct formula is found it will print out 'impossible'.
 * @author Nikolah Pearce.
 */

public class Arithmetic {

    /** Array List of integer input values to use in the calculations */
    private ArrayList<Integer> input; 
    /** The target value we want our calculation to be */
    private int goal;
    /** Single character to specify the order of operations to be used */
    private String order;
    /** Boolean to use as a break case in the recursive calculations */
    private boolean foundAnswer = false;
    /** The impossible string to return when needed */
    private static final String FAIL = "impossible";

    /**
     * Takes two input lines and sets all of the data fields.
     * @param line1 the line of integers to use in our calculations.
     * @param line2 the target value and order of operaions to use.
     */
    public void setVariables(String line1, String line2) {
        
        String[] inputArray = line1.split(" ");
        String[] goalString = line2.split(" ");
        if (goalString.length < 2) {
            System.err.println("Second line requires to arguments: int char");
            return;
        }
        // Set the input array and do basic checks
        input = new ArrayList<Integer>();
        try {
            for (int i = 0; i < inputArray.length; i++) {
                input.add(Integer.parseInt(inputArray[i]));
            }
        } catch (NumberFormatException e) {
            System.err.println("You must enter valid input numbers seperated by a space ");
            return;
        }
        // Set the target value and do basic checks
        try {
            goal = Integer.parseInt(goalString[0]);
        } catch (NumberFormatException e) {
            System.err.println("The target value must be a valid integer number.");
            return;
        }
        // Set order character and do basic checks
        order = goalString[1];
        if (!order.equals("N") && !order.equals("L")) {
            System.err.println("The first character needs to be N or L.");
            return;
        } 
    }

    /**
     * Perform the actual calculation to work out the answer.
     * Call all necessary helper methods too.
     */
    public void calculate() {
        String ops = "";
        // Check which recursive function we need to call, and call it.
        if (order.equals("L")) {
            foundAnswer = false;
            ops += leftToRight("", input.get(0), 0);
        } else if (order.equals("N")) {
            foundAnswer = false;
            ops += normal(input, "", 0);
        }
        // Format the output with the input and target values
        System.out.print(order + " " + goal + " ");
        if (ops.equals("impossible")) {
            System.out.print(ops);
        } else {
            int i = 0;
            for (int n = 0; n < input.size(); n++) {
                System.out.print(input.get(n));
                if (i < ops.length()) {
                    System.out.print(" " + ops.charAt(i) + " ");
                    i++;
                } 
            }
        }
        System.out.print("\n");
    }

    /**
     * Performs a recursive search through an array of ints.
     * Looks for whether any operations of + or * can create target value.
     * Also ignores BEDMAS and runs left to right.
     * @param ops the String of all previous operations.
     * @param prevTotal the previous total from operations performed.
     * @param i the current index.
     * @return the string of concatenated operators e.g. "+*+".
     */
    private String leftToRight(String ops, int prevTotal, int i) {
        if (foundAnswer == true) {
            return FAIL;
        }
        if (prevTotal == goal && i == input.size()) {
            foundAnswer = true;
            if (ops.length() == (input.size())) {
                return ops.substring(0, (ops.length()-1));
            } else {
                return ops;
            }
        }
        if (prevTotal > goal || i >= input.size())  {
            return FAIL;
        }
        int total = prevTotal;
        if (!(ops.length() == 0)) {
            String thisOp = ops.substring((ops.length()-1));
            if (thisOp.equals("+")) {
                total += input.get(i);
            } else if (thisOp.equals("*")) {
                total *= input.get(i);
            }
        }        
        i++;
        String left = leftToRight(ops+"+", total, i);
        String right = leftToRight(ops+"*", total, i);
        if (!left.equals(FAIL)) {
            return left;
        } else if (!right.equals(FAIL)) {
            return right;
        } 
        
        return FAIL;
    }

    /**
     * Performs a recursive search through an array of ints.
     * Looks for whether any operations of + or * can create target value.
     * Does not ignore BEDMAS and calculates multiplication first.
     * @param inputIn the array list to copy and work on
     * @param ops the String of al previous operations.
     * @param i the current index.
     * @return the string of concatenated operators e.g. "+*+".
     */
    private String normal(ArrayList<Integer> inputIn, String ops, int i) {  
        if (foundAnswer == true) {
            return FAIL;
        }
        if (i >= inputIn.size()) {
            int all = 0;
            for (int n = 0; n < inputIn.size(); n++) {
                all += inputIn.get(n);
            }
            if (all == goal) {
                foundAnswer = true;
                if (ops.length() == input.size()) {
                    return ops.substring(0, (ops.length()-1));
                } else {
                    return ops;
                }
            } 
            return FAIL;
        }
        
        ArrayList<Integer> inputCopy = new ArrayList<Integer>(inputIn);

        if (!(ops.length() == 0)) {
            String thisOp = ops.substring((ops.length()-1));
            if ((thisOp.equals("*")) && (i < input.size())) {  
                int product = (inputCopy.get(i-1) * inputCopy.get(i));
                inputCopy.set(i, product);
                inputCopy.set(i-1, 0);
            }
        } 
        i++;
        String left = normal(inputCopy, ops+"+", i);
        String right = normal(inputCopy, ops+"*", i);
        if (!left.equals(FAIL)) {
            return left;
        } else if (!right.equals(FAIL)) {
            return right;
        } 
        return FAIL;
    }
    
}  
