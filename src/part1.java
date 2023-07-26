
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
import java.io.FileWriter;
import java.io.IOException;

public class part1 {
    public static void main(String[] args) {

        // Try-catch block to handle IO exception
        try {
            // Creates an instance of the BufferedReader class
            BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));

            String line = null;
            line = buffer.readLine();// Contains the files to read

            int numofLines = Integer.parseInt(line);// Turns string into integer to find the amount of lines we have in
                                                    // the txt

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
                    BufferedReader filescsv = new BufferedReader(reader);
                    String line1 = filescsv.readLine();
                    String[] fields = line1.split(",");// Splits the line of text at the ","
                    int numofFields = fields.length;
                    FileWriter write = new FileWriter("synthax_error_file");
                    int empty = 0;
                    String missingfield = "";

                    // Checks for synthax errors
                    while ((line1 = filescsv.readLine()) != null) {
                        try {
                            // Checks for the SportsName
                            if (fields[1].equals("Football")) {
                                System.out.println();
                            } else if (fields[1].equals("Hokey")) {
                                System.out.println();
                            } else if (fields[1].equals("Basketball")) {
                                System.out.println();
                            } else {
                                throw new UnknownSportException("This sport does not exist");
                            }

                            // Checks for the number of fields
                            if (numofFields > 5) {
                                throw new TooManyFieldsException("To many fields");
                            } else if (numofFields < 5) {
                                throw new TooFewFieldsException("Not enough fields");
                            } else {
                                for (int z = 0; z < numofFields; z++) {
                                    if (fields[i] == null || fields[i].isEmpty()) {
                                        empty = i;
                                        throw new MissingFieldException("Some fields are missing");
                                    }

                                }
                            }
                        } catch (UnknownSportException u) {

                        } catch (TooManyFieldsException m) {
                            write.write("synthax error in file:" + filescsv
                                    + "\n=============\nError: Too many Fields\nRecord: " + line1 + "\n\n");

                        } catch (TooFewFieldsException f) {
                            write.write("synthax error in file:" + filescsv
                                    + "\n=============\nError: Too few Fields\nRecord: " + line1 + "\n\n");

                        } catch (MissingFieldException s) {

                            // Checks which field is missing
                            if (empty == 0) {
                                missingfield = "TeamName field is missing";
                            } else if (empty == 1) {
                                missingfield = "SportType field is missing ";
                            } else if (empty == 2) {
                                missingfield = "Year field is missing";
                            } else if (empty == 3) {
                                missingfield = "Team Record field is missing";
                            } else if (empty == 4) {
                                missingfield = "championship field is missing";
                            }
                            write.write("synthax error in file:" + filescsv + "\n=============\nError: " + missingfield
                                    + "\nRecord: " + line1);

                        }
                    }
                    reader.close();// Closes the reader

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}