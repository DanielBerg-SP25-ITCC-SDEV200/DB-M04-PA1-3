import java.io.BufferedReader; // Import the BuffedReader class to read text from a file.
import java.io.FileReader; // Import the FileReader class to read file contents.
import java.io.IOException; // Import the IOException class for handling file reading errors.
import java.util.Stack; // Import the Stack class to manage a collection of characters.

public class GroupingSymbolsChecker { // Define the main class of the program.
    public static void main(String[] args) { // Main method to execute the program.
        if (args.length != 1) { // Check if the number of arguements passed to the program is not 1.
            System.out.println("Usage: java GroupingSymbolsChecker <source-file>"); // Print usage instructions if arguements are incorrect
            return; // Exit the method if the correct number of arguments is not passed.
        }

        String fileName = args[0]; // Get the file name from the command line arguments.
        try { // Start of the try block to handle file reading and processing. 
            BufferedReader reader = new BufferedReader(new FileReader(fileName)); // Create a BufferedReader to read the file line by line.
            String line; // Declare a variable to hold each line of the file.
            Stack<Character> stack = new Stack<>(); // Create a stack to stor opening symbols (e.g., (, {, [).

            while ((line = reader.readLine()) != null) { // Read the file line by line until the end of the file.
                for (char ch : line.toCharArray()) { // Iterate through each character in the current line.
                    if (ch == '(' || ch == '{' || ch == '[') { // Check if the character is an opening symbol.
                        stack.push(ch); // Push the opening symbol onto the stack.
                    } else if (ch == ')' || ch == '}' || ch == ']') { // Check if the character is a closing symbol.
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) { // If stack is empty or symbols don't match, report mismatch. 
                            System.out.println("Mismatch found: " + ch + " at line: " + line); // Print the mismatch message with the line where it occured.
                            reader.close(); // Close the reader as we don't need to continue checking.
                            return; // Exit the method as the mismtach is found.
                        }
                    }
                }
            }

            if (!stack.isEmpty()) { // Check if there are any unmatched opening symbols left in the stack.
                System.out.println("Unmatched opening symbols remain."); // Print message indicating unmatched opening symbols.
            } else { // If the stack is empty, it means all openign symols were correctly matched.
                System.out.println("All grouping symbols are matched correctly."); // Print message indicating all symbols are properly matched.
            }

            reader.close(); // Close the reader after reading all lines.
        } catch (IOException e) { // Catch any IOException that occurs while reading the file.
            System.out.println("Error reading file: " + e.getMessage()); // Print an error message if there's a problem reading the file.
        }
    }

    private static boolean isMatchingPair(char opening, char closing) { // Method to check if the opening and closing symbols match.
        return (opening == '(' && closing == ')') || // Check if parentheses match.
                (opening == '{' && closing == '}') || // Check if curly braces match.
                (opening == '[' && closing == ']'); // Check if square brackets match.
    }
}