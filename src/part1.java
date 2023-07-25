import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class part1 {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("Part1_input_file_names.txt");
            int i = reader.read();
            while (i != -1) {
                i=reader.read();
                reader.close();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();

        }
    }
}
