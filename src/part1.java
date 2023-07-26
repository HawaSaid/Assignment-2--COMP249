/**
* Hawa-Afnane Said (ID:40263400)
* Haifaa Janoudi (ID:40263748)
* COMP249
* Assignment 2 
* Due date:27/07/2023
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {

        // Try-catch block to handle IO exception
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));
            String line = null;
            line = buffer.readLine();
            int numofLines = Integer.parseInt(line);
            int i = 0;
            String[] readFile = new String[numofLines];
            // Loop to read the txt files containing the CSV files
            while ((line = buffer.readLine()) != null) {

                // Array of type String created to store the CSV files in the txt file
                readFile[i] = line;// Content of file stored in readFile array
                i++;
            }
            buffer.close();// Closes buffer

            // For loop to read the CSV files
            for (int j = 0; j < readFile.length; j++) {
                try {
                    FileReader reader = new FileReader(readFile[j]);// Creates an instance of the FileReader class
                    int files = reader.read();// Contains the number of lines

                    // Checks if the line being read is not empty and reads content of each file
                    while (files != -1) {
                        files = reader.read();
                    }
                    reader.close();// Closes the reader
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
