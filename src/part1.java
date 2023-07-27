
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

    public void do_part1() {

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
                BufferedReader filescsv = new BufferedReader(new FileReader(readFile[j]));
                String line1;// Reads each line of the csv file
                try {
                    // Creates an instance of the BufferedReader class to read each csv file
                    // Checks for synthax errors when line1 is not equal to null
                    while ((line1 = filescsv.readLine()) != null) {
                        String[] fields = line1.split(",");// Splits the line of text at the "," and stores the content
                                                           // in array fields
                        int numofFields = fields.length;
                        try {
                            // Checks if the String "Hokey","Basketball"&"Football" is in the String line1
                            if (!(line1.contains("Hokey")) && !(line1.contains("Basketball"))
                                    && !(line1.contains("Football"))) {
                                throw new UnknownSportException("Unknown sport field");// Throws exception if not in
                                                                                       // line1
                            }
                        } catch (UnknownSportException u) {
                            synthax1.println("synthax error in file:" + readFile[j]
                                    + "\n======================================\nError:" + u.getMessage() + "\nRecord:"
                                    + line1 + "\n\n");
                        }
                        int empty = -1;// Shows there's no empty file
                        // Checks for the number of fields

                        try {
                            // Checks numofFields
                            if (numofFields > 5) {
                                throw new TooManyFieldsException("To many fields");
                            } else if (numofFields < 5) {
                                throw new TooFewFieldsException("Not enough fields");
                                // This else never works porque tho?
                            } else {
                                for (int z = 0; z < fields.length; z++) {
                                    if (fields[z] == null || fields[z].trim().isEmpty()) {
                                        empty = z;
                                        throw new MissingFieldException("Field is missing");
                                    }
                                }
                            }
                        } catch (TooManyFieldsException m) {
                            synthax1.println("synthax error in file:" + readFile[j]
                                    + "\n======================================\nError: Too many Fields\nRecord: "
                                    + line1 + "\n\n");

                        } catch (TooFewFieldsException f) {
                            synthax1.println("synthax error in file:" + readFile[j]
                                    + "\n======================================\nError: Too few Fields\nRecord: "
                                    + line1
                                    + "\n\n");

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
                                    "synthax error in file:" + readFile[j]
                                            + "\n======================================\nError: " + missingfield
                                            + "\nRecord: " + line1 + "\n\n");
                        }
                        boolean Empty = false;
                        boolean validate = (line1.contains("Hokey") || line1.contains("Football")
                                || line1.contains("Basketball")) && numofFields == 5;
                        // If statement to check what happens when boolean validate is true
                        if (validate) {
                            Empty = false;
                            // For loop to check if each cell of the array is either empty or blank
                            for (int h = 0; h < numofFields; h++) {
                                if (fields[h] == null || fields[h].trim().isEmpty()) {
                                    Empty = true;
                                    break;
                                }
                            }
                            if (!Empty) {
                                if (line1.contains("Hokey")) {
                                    hokey1.println(line1);
                                } else if (line1.contains("Football")) {
                                    football1.println(line1);
                                } else if (line1.contains("Basketball")) {
                                    basket1.println(line1);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                filescsv.close();// Closes the filescsv

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