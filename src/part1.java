import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {

        // Try-catch block to handle IO exception
        try {
            // Creates an instance of the BufferedReader class
            BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));

            String line = null;

            line = buffer.readLine();// Contains the files to read
            int numberofLines = 0;
            int i = 0;

            // Loop to find the amount of lines we have in the txt
            while ((line = buffer.readLine()) != null) {
                numberofLines++;
            }
            // Loop to read the txt files containing the CSV files
            while ((line = buffer.readLine()) != null) {
                
                // Array of type String created to store the CSV files in the txt file
                
                String[] readFile = new String[numberofLines];
                readFile[i] = line;// Content of file stored in readFile array
                i++;
            }
            buffer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
