import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {

        // Try-catch block to handle IO exception
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));// Creates an
                                                                                                     // instance of the
                                                                                                     // BufferedReader
                                                                                                     // class

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
            buffer.close();

            // For loop to read the CSV files
            for (int j = 0; j < readFile.length; j++) {
                try {
                    FileReader reader = new FileReader(readFile[j]);
                    int files = reader.read();
                    // Checks if the line being read is not empty, if its empty, files=-1
                    if (files != -1) {
                        System.out.println();
                    }
                    reader.close();
                } catch (FileNotFoundException e1) {

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
