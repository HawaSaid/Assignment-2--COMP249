import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;
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

	/**
	 * This method reads the first text file.
	 * The name of the files in the text file is then stored in a String array.
	 * Then, every file is being read while checking for synthax errors
	 * Each line is stored in a String array split at the ",".
	 * If the error encountered is about the sport, a unknown sport exception is
	 * thrown.
	 * If the error encountered is about the amount of field, a to many field or to
	 * few fields exception is being thrown
	 * When a line is missing a field, a missing field exception is being thrown.
	 * All those exceptions are then catched and then printed in the
	 * synthax_error_file.
	 * The remaining lines with no exception are then printed in the new csv files.
	 */
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
						} catch (UnknownSportException u) {// Prints segment showing error and what line throws this
															// error
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

						// If statement checking if validate is true
						if (validate) {
							Empty = false;
							// For loop to check if each cell of the array is either empty or blank
							for (int h = 0; h < numofFields; h++) {
								if (fields[h] == null || fields[h].trim().isEmpty()) {
									Empty = true;
									break;
								}
							}
							// If statement to print the data in line1 in either one of the three csv files
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

	/**
	 * This method checks for semantic errors
	 * The files in the array filename are first being read
	 * If a line in the files contain a bad year record, a bad year exception is
	 * being thrown
	 * If a line in the files contain a bad record, a bad record exception is being
	 * thrown
	 * The lines containing exceptions are then printed in the semantic_error_file
	 * txt file.
	 * The lines containing no exception are then printed in a serialized file
	 * depending on the sport name they have
	 */
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
							if (!(line.contains("2001")) && !(line.contains("2011"))
									&& !(line.contains("2019"))) {
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
						boolean verify = ((line.contains("2001")) || (line.contains("2011"))
								|| line.contains("2019"));

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
	/**
	 * This method deserializes the files that were serialized in the do_part2() method and makes an interractive menu
	 * The files are first put in that string array and then deserialized one by one(doesn't work properly)
	 * Each object of the football file is put an array, and the hockey objects in another array, etc.
	 * These arrays are then used to fill the finalarr array.
	 * The menu for the interactive is being printed & the field menu is also printed
	 * 
	 */

	public static void do_part3() {

		String[] BinaryFile = { "Football.csv.ser", "Hokey.csv.ser", "Basketball.csv.ser" }; // array of files to read
		int[] numofObjperfile = { 2, 5, 5 };

		// Deserialization of the objects in the binary files
		try {
			for (int i = 0; i < BinaryFile.length; i++) {
				// Deserializes the three files
				FileInputStream fileIn = new FileInputStream(BinaryFile[i]);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				Team[] arr = new Team[numofObjperfile[0]];
				Team[] arr1 = new Team[numofObjperfile[1]];
				Team[] arr2 = new Team[numofObjperfile[2]];
				Team[] finalarr = new Team[12];
				int keepstrack = 0;

				if (numofObjperfile[0] == 2) {
					for (int j = 0; j < numofObjperfile[0]; j++) {
						arr[j] = (Team) in.readObject();
					}
				} else if (numofObjperfile[1] == 5)
					for (int j = 0; j < numofObjperfile[1]; j++) {
						arr1[j] = (Team) in.readObject();
					}
				else if (numofObjperfile[2] == 5) {
					for (int j = 0; j < numofObjperfile[2]; j++) {
						arr2[j] = (Team) in.readObject();
					}
				}
				// Storing the content in the finalarr array
				for (int z = 0; z < arr.length; z++) {
					finalarr[keepstrack++] = arr[z];
				}
				for (int z = 0; z < arr1.length; z++) {
					finalarr[keepstrack++] = arr1[z];
				}
				for (int z = 0; z < arr2.length; z++) {
					finalarr[keepstrack++] = arr2[z];
				}

				Scanner keys = new Scanner(System.in);
				int n = 0;
				int h = 0;

				do {
					System.out.println("Enter a value from 0 to x");
					n = keys.nextInt();
					if (n == 0)
						break;
					else if (n > 0)
						for (int k = 0; k <= n - 1; k++) {
							if (h + k < finalarr.length) {
								System.out.println(finalarr[h + k]);
								if (k == n - 1)
									h = h + k;
								else {
									System.out.println("EOF has beeen reached");
									h = finalarr.length - 1;
									break;
								}
							}

							else {
								int l = ((-1) * n) - 1;
								int r = 0;
								int values = 0;
								for (k = l; k >= 0; k--) {
									if (h - k < 0) {
										System.out.println("BOF has beeen reached");
										k = h + 1;

									} else {
										if (values == 0)
											r = 0;
										r = i - k;
										System.out.println(finalarr[i - k]);
									}

								}
							}

						}
				} while (n != 0);

				// Closes the FileInputStream & ObjectInputStream
				in.close();
				fileIn.close();
				keys.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		// Interactive code that will navigate the user depending on their choices
		Scanner key = new Scanner(System.in);
		// char input;

		System.out.println("---------------------");
		System.out.println("Main Menu");
		System.out.println("---------------------");
		System.out.println("v View the selected file:" );
		System.out.println("s Select a file to view:");
		System.out.println("x Exit");
		System.out.println("---------------------");
		System.out.println("Kindly,enter your choice");

		char input = key.next().charAt(0);
		do {
		if (input == 's' || input == 'S') {
			System.out.println("---------------------");
			System.out.println("File Sub-Menu");
			System.out.println("---------------------");
			System.out.println("1 " + "" + BinaryFile[0] + "(" + (numofObjperfile[0]) + " records)");
			System.out.println("2 " + "" + BinaryFile[1] + "(" + (numofObjperfile[1]) + " records)");
			System.out.println("3 " + "" + BinaryFile[2] + "(" + (numofObjperfile[2]) + " records)");
			System.out.println("4 " + "" + "Exit");
			System.out.println("---------------------");
			System.out.println("Kindly,enter your choice");
			input = key.next().charAt(0);
		} else if (input == 'v' || input == 'V') {
			System.out.println("Viewing: " + BinaryFile[0] + " (" + (numofObjperfile[0]) + " records)");
				System.out.println("Kindly,enter your choice");
				 input = key.next().charAt(0);
		} else if (input == 'x' || input =='X') {
				System.out.println("Exiting the program");
				System.out.println("---------------------");
				System.out.println("Kindly,enter your choice");
				 input = key.next().charAt(0);

				}
		}
			while (input != 'x' || input !='X');
			
	}
}
