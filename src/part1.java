
/**
* Hawa-Afnane Said (ID:40263400)
* Haifaa Janoudi (ID:40263748)
* COMP249
* Assignment 2 
* Due date:27/07/2023
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

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

            File hokey = new File("Hokey.csv");
            File football = new File("Football.csv");
            File basket = new File("Basketball.csv");
            File synthax = new File("synthax_error_file.txt");
            PrintWriter hokey1 = new PrintWriter(hokey);
            PrintWriter football1 = new PrintWriter(football);
            PrintWriter basket1 = new PrintWriter(basket);
            PrintWriter synthax1 = new PrintWriter(synthax);

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
                    // Creates an instance of the BufferedReader class
                    BufferedReader filescsv = new BufferedReader(new FileReader(readFile[j]));
                    String line1 = filescsv.readLine();
                    String[] fields = line1.split(",");// Splits the line of text at the "," and stores the content in
                                                       // array
                    // Checks for synthax errors
                    while ((line1 = filescsv.readLine()) != null) {
                        int numofFields = fields.length;
                        try {
                            // Checks if the String "Hokey","Basketball"&"Football" is in the String line1
                            if (!(line1.contains("Hokey")) && !(line1.contains("Basketball"))
                                    && !(line1.contains("Football"))) {
                                throw new UnknownSportException("Unknown sport field");// Throws exception if not in line1
                            }
                        } catch (UnknownSportException u) {
                            synthax1.println("synthax error in file:" + readFile[j]
                                    + "\n=============\nError:"+u.getMessage()+"\nRecord:" + line1 + "\n\n");// Prints
                                                                                                                // this
                                                                                                                // in
                                                                                                                // the
                                                                                                                // syntax
                                                                                                                // error
                                                                                                                // txt
                        }
                        int empty = 0;
                        // Checks for the number of fields

                        try {
                            // Checks numofFields
                            if (numofFields > 5) {
                                throw new TooManyFieldsException("To many fields");
                            } else if (numofFields < 5) {
                                throw new TooFewFieldsException("Not enough fields");
                            } else {
                                for (int z = 0; z < numofFields; z++) {
                                    if (fields[z] == null || fields[z].isEmpty()) {
                                        empty = z;
                                        throw new MissingFieldException("Field is missing");
                                    }
                                }
                            }
                            if (line1.contains("Hokey") && numofFields == 5
                                    && (!(fields[j] == null) || fields[j].isEmpty())) {
                                hokey1.println(line1);
                            } else if (line1.contains("Basketball") && numofFields == 5
                                    && (!(fields[j] == null) || fields[j].isEmpty())) {
                                basket1.println(line1);
                            } else if (line1.contains("Football") && numofFields == 5
                                    && (!(fields[j] == null) || fields[j].isEmpty())) {
                                football1.println(line1);
                            }
                        } catch (TooManyFieldsException m) {
                            synthax1.println("synthax error in file:" + readFile[j]
                                    + "\n=============\nError: Too many Fields\nRecord: " + line1 + "\n\n");

                        } catch (TooFewFieldsException f) {
                            synthax1.println("synthax error in file:" + readFile[j]
                                    + "\n=============\nError: Too few Fields\nRecord: " + line1 + "\n\n");

                        } catch (MissingFieldException s) {
                            String missingfield = "";
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
                            synthax1.println(
                                    "synthax error in file:" + readFile[j] + "\n=============\nError: " + missingfield
                                            + "\nRecord: " + line1 + "\n\n");
                        }

                    }
                    filescsv.close();// Closes the filescsv
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // Closes all printWriters
            hokey1.close();
            football1.close();
            basket1.close();
            synthax1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}