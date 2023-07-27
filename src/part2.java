

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.File;

public class part2 {

	public static void main(String[] args) {
		BufferedReader br = null;
		String[] fileName = { "Basketball.csv", "Football.csv", "Hokey.csv" }; // array of files to read

		// a for loop to read the information in the files
		for (int i = 0; i < fileName.length; i++) {
			try {
				br = new BufferedReader(new FileReader(fileName[i]));
				System.out.println("Reading the content of each file:");
				String contentLine = br.readLine();
				while (contentLine != null) {
					System.out.println(contentLine);
					contentLine = br.readLine();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ioe) {
					System.out.println("Information Not Found!");
				}
			}
		}

	}

	File Semantic = new File("semantic_error_file.txt");
	PrintWriter Semantic1 = null;
	
	

	public static void semanticException(int year, String Record)
			throws BadRecordException, BadYearException, FileNotFoundException {
		boolean Semantic = true;

		try {
			// PrintStream printstream = new PrintStream("Semantic_error_file.txt");
			// System.setErr(printstream);
		
			
			if (year != 2001 || year != 2011 || year != 2019) {
				Semantic = false;
				throw new BadYearException("The year does not correspond to any of the records available");
			} else if (Record != "Football" || Record != "Hokey" || Record != "Basketball") {
				Semantic = false;
				throw new BadRecordException("The sport inserted does not correspond to any of the records");
			}

		}

		catch (BadYearException e) {
			//Semantic1.println("The year does not correspond to any of the mentioned years");
		} 
		
		catch (BadRecordException o) {

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
