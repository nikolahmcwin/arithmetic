# arithmetic
Individual assignment for COSC326 March 2018. Given a set of input numbers and a desired output, determine if you can use + and * only to create the output.

@author Nikolah Pearce

Arithmetic.java takes input from stdin in the form of 2 consecutive lines, e.g.
1 2 3
7 N

It will either tell you the operatiosn found to reach the goal, or print out impossible e.g.
N 7 1 + 2 * 3
N impossible


# Task

Input from stdin will be a sequence of scenarios. Each scenario is exactly two lines. The first line is the numbers to use, and the second line consists of a target value, fol- lowed by a space, followed by the character N or the character L indicating whether normal order of operations, or left to right order is to be assumed.

The output for a scenario should be a character representing the order used, then the target value, and finally an expression of the required value (using the character * for multiplication, and with spaces before and after each operation), or the word impossible.

