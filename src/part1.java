import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {

        // Try-catch block to handle IO exception
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));// Creates an instance of the BufferedReader class
            String line = null;
            line = buffer.readLine();// Contains the files to read
            int numberofLines = 0;
            int i = 0;
            String[] readFile=new String[line.length()];

            // Loop to find the amount of lines we have in the txt
            while ((line = buffer.readLine()) != null) {
                numberofLines++;
            }
            // Loop to read the txt files containing the CSV files
            while ((line = buffer.readLine()) != null) {

                // Array of type String created to store the CSV files in the txt file

                readFile = new String[numberofLines];
                readFile[i] = line;// Content of file stored in readFile array
                i++;
            }
            buffer.close();

            //For loop to read the CSV files
            for (int j = 0; j < readFile.length; j++) {
                    try{
                    FileReader reader=new FileReader(readFile[j]);
                    int files=reader.read();
                    //Checks if the line being read is not empty, if its empty, files=-1
                    while(files!=-1){
                        System.out.println("Here is the content of the file you want to read: "+"Works");
                    }
                    reader.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }

            } catch(IOException e){
        e.printStackTrace();
        }
    }

}
