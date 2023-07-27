import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class part2 {

	public static void main(String[] args) {
		BufferedReader br = null;
		String[] fileName = { "Basketball.csv", "Football.csv", "Hokey.csv" }; // array of csv files to read
		File semantic = new File("syntax_error_file.txt");
		try {
			PrintWriter semantic1 = new PrintWriter(semantic);
			String[] readFile = new String[fileName.length];
			for (int i = 0; i < fileName.length; i++) {
				try {
					BufferedReader buffer = new BufferedReader(new FileReader(fileName[i]));
					String line = null;
					while ((line = buffer.readLine()) != null) {
						String[] anotherline = line.split(",");// Content of line stored in array anotherLine
						int field = anotherline.length;
						// Check for semantic errors
						try {
							if (!(line.contains("2001")) || !(line.contains("2010")) || !(line.contains("2019"))) {
								throw new BadYearException("Bad year for field");
							} else {
								//Checks for bad records
								for (int j = 0; j < field; j++) {
									if () {
										throw new BadRecordException("Bad record for field");
									}
								}
							}
						} catch (BadYearException e) {
							semantic1.println("synthax error in file:" + readFile[i]
									+ "\n======================================\nError:" + e.getMessage() + "\nRecord:"
									+ line + "\n\n");
						} catch (BadRecordException e1) {
							semantic1.println("synthax error in file:" + readFile[i]
									+ "\n======================================\nError:" + e1.getMessage() + "\nRecord:"
									+ line + "\n\n");
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			semantic1.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}