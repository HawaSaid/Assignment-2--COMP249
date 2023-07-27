import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;

/**
 * Hawa-Afnane Said (ID:40263400)
 * Haifaa Janoudi (ID:40263748)
 * COMP249
 * Assignment 2
 * Due date:27/07/2023
 */

public class Driver {

	public static void main(String[] args) {
		do_part1();
		do_part2();
		// do_part3();
	}

	public static void do_part1() {

		// Try-catch block to handle IO exception
		try {
			// Creates an instance of the BufferedReader class
			BufferedReader buffer = new BufferedReader(new FileReader("Part1_input_file_names.txt"));
			String line = null;
			line = buffer.readLine();

			int numofLines = Integer.parseInt(line);// Turns string into integer to find the amount of lines we have in
													// the txt

			int i = 0;

			File hokey = new File("Hokey.csv.ser");
			File football = new File("Football.csv.ser");
			File basket = new File("Basketball.csv.ser");
			File synthax = new File("synthax_error_file.txt");
			PrintWriter hokey1 = new PrintWriter(hokey);
			PrintWriter football1 = new PrintWriter(football);
			PrintWriter basket1 = new PrintWriter(basket);
			PrintWriter synthax1 = new PrintWriter(synthax);

			String[] readFile = new String[numofLines];// Array containing the names of each file

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

	public static void do_part2() {

		String[] fileName = { "Basketball.csv", "Football.csv", "Hokey.csv" }; // array of csv files to read

		try {

			File semantic = new File("semantic_error_file.txt");
			PrintWriter semantic1 = new PrintWriter(semantic);
			for (int i = 0; i < fileName.length; i++) {
				BufferedReader buffer = new BufferedReader(new FileReader(fileName[i]));
				String line = null;
				try {			
					while ((line = buffer.readLine()) != null) {

						String[] anotherline = line.split(",");// Content of line stored in array anotherLine

						// Check for semantic errors
						try {
							// Checks if 2001/2011/2019 is not contained in the second array cell
							if (!(anotherline[2].equals("2001")) && !(anotherline[2].equals("2011"))
									&& !(anotherline[2].equals("2019"))) {
								throw new BadYearException("Bad year for field");
							} else {
								// Checks for bad records
								String[] validate = anotherline[3].split("-");// Splits the array cell where the record
																				// is contained by the ","
								int check = validate.length;
								if (check != 2 && anotherline[3] != null) {
									throw new BadRecordException("Bad record for field");
								}
							}
						} catch (BadYearException e) {
							semantic1.println("synthax error in file:" + fileName[i]
									+ "\n======================================\nError:" + e.getMessage() + "\nRecord:"
									+ line + "\n\n");
						} catch (BadRecordException e1) {
							semantic1.println("synthax error in file:" + fileName[i]
									+ "\n======================================\nError:" + e1.getMessage() + "\nRecord:"
									+ line + "\n\n");
						}
						String[] validate = anotherline[3].split("-");
						int check = validate.length;
						boolean record = check == 2 && anotherline[3] != null;
						boolean verify = ((anotherline[2].equals("2001")) || (anotherline[2].equals("2011"))
								|| anotherline[2].equals("2019"));

						if (record && verify) {
							String name = anotherline[0];
							String sport = anotherline[1];
							String year = anotherline[2];
							String score = anotherline[3];
							String championship = anotherline[4];
							Team obj = new Team(name, sport, year, score, championship);
							if (anotherline[1].equals(" Hokey") || anotherline[1].equals("Hokey")) {
								// Serialization
								try {
									ObjectOutputStream hokeyfile = new ObjectOutputStream(
											new FileOutputStream("Hokey.csv.ser"));
									hokeyfile.writeObject(obj);
									hokeyfile.close();
								} catch (IOException e) {
									e.printStackTrace();
								}

							} else if (anotherline[1].equalsIgnoreCase(" Football")
									|| anotherline[1].equalsIgnoreCase("Football")) {
								// Serialization
								try {
									ObjectOutputStream footballfile = new ObjectOutputStream(
											new FileOutputStream("Football.csv.ser"));
									footballfile.writeObject(obj);
									footballfile.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else if (anotherline[1].equals(" Basketball") || anotherline[1].equals("Basketball")) {
								// Serialization
								try {
									ObjectOutputStream basketballfile = new ObjectOutputStream(
											new FileOutputStream("Basketball.csv.ser"));
									basketballfile.writeObject(obj);
									basketballfile.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

						}

					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				buffer.close();
			}
			
			// Closes the PrintWriter
			semantic1.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
