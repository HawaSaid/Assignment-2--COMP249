import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {

        //Try-catch block to handle IO exception
        try {
            // Creates an instance of the BufferedReader class
            BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));

            String line = null;

            line = buffer.readLine();// Contains the files to read
            int i = 0;

            // Array of type String created to store the CSV files in the txt file
            String readFile[] = new String[line.length()];

            // Loop to read the txt file containing the CSV files
            while ((line = buffer.readLine()) != null) {
                readFile[i] = line;
                i++;
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
