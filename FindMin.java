import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* This program finds the minimum number in an array.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-04-28
*/

public final class FindMin {
    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private FindMin() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        // Declare list and variable.
        final ArrayList<String> inputList = new ArrayList<String>();
        String minStr = "";
        final String sp = " ";

        try {
            // Choose a file to get input from.
            final File input = new File("input.txt");
            final Scanner scanInput = new Scanner(input);

            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("output.txt");

            // Loop to read each line of input file.
            while (scanInput.hasNextLine()) {
                // Add the next line.
                inputList.add(scanInput.nextLine());
            }

            // Create an array with all elements in the input list.
            final String[] inputArr = new String[inputList.size()];
            for (int location = 0; location < inputArr.length; location++) {
                inputArr[location] = inputList.get(location);
            }

            // Loop to send each array to function.
            for (int counter = 0; counter < inputArr.length; counter++) {
                try {
                    // Convert inputArr to int.
                    final int size = inputArr[counter].split(sp).length;
                    final int[] arrInt = new int[size];
                    for (int location = 0; location < size; location++) {
                        arrInt[location] = Integer.parseInt(
                            inputArr[counter].split(sp)[location]);
                    }

                    // Find lowest number.
                    final int min = findLowest(arrInt, 0);
                    System.out.println(min);
                    minStr = minStr + min + "\n";
                } catch (NumberFormatException err) {
                    // For when line does not contain ints.
                    minStr = minStr + "Line could not be converted to int.\n";
                    System.out.println("Line could not be converted to int.");
                }
            }

            // Write to output file.
            output.write(minStr);

            // Close writer.
            output.close();

        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
    }

    /**
    * This function finds the lowest number of an array.
    *
    * @param listOfNum from file
    * @param index starts at 0
    * @return lowest number
    */
    public static int findLowest(int[] listOfNum, int index) {
        // Base case: The end of the array is reached, return the last element.
        if (index == listOfNum.length - 1) {
            return listOfNum[index];
        }

        // Find the lowest element in the rest of the array
        final int lowest = findLowest(listOfNum, index + 1);

        // Check if the element is lower than the current lowest element.
        if (listOfNum[index] < lowest) {
            return listOfNum[index];
        } else {
            return lowest;
        }
    }
}
