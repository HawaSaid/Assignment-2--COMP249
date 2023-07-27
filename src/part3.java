

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class part3 {
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		BufferedReader br = null;
		String[] BinaryFile = { "Basketball.csv.ser", "Football.csv.ser", "Hokey.csv.ser" }; // array of files to read
		

		/*// a for loop to read the information in the files
		for (int i = 0; i < BinaryFile.length; i++) {
			try {
				br = new BufferedReader(new FileReader(BinaryFile[i]));
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
		}*/
		
		
		//Deserialization of objects in the  binary files

		for (int i = 0; i < BinaryFile.length; i++) {
		try {
			FileInputStream fileIn = new FileInputStream(BinaryFile[i]);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			System.out.println("Deserialized Data: \n" + in.readObject().toString());
			in.close();
			fileIn.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + "not able to create the binary file!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		}
		
		
		
		
				
				
				
		//Interactive code that will navigate the user depending on their choices
		
		String input = "";
		String option = "";
		int Option = 0;
		int file = 0;
		while (!input.equalsIgnoreCase("x")) {
			
		
		System.out.println("---------------------");
		System.out.println("Main Menu");
		System.out.println("---------------------");
		System.out.println("v View the selected file: ");
		System.out.println("s Select a file to view:");
		System.out.println("x Exit");
		System.out.println("---------------------");
		System.out.println("Kindly,enter your choice");
		input = key.next();
		if (input.equals("s")) {
			System.out.println("---------------------");
			System.out.println("File Sub-Menu");
			System.out.println("---------------------");
			System.out.println("1." + " " + BinaryFile[0] + "(" + (BinaryFile[0]+1) + " records)");
			System.out.println("2." + " " + BinaryFile[1] + "(" + (BinaryFile[1]+1) + " records)");
			System.out.println("3." + " " + BinaryFile[2] + "(" + (BinaryFile[2]+1) + " records)");
			System.out.println("4." + " " + "Exit");
			System.out.println("---------------------");
			System.out.println("Please enter your choice");
			option = key.next();
			int n = Integer.parseInt(option);
			if(option.equalsIgnoreCase("x")) {
				System.exit(0);
			}
			else if(n==0){
				return;
			}
			else if (n>0) {
				//for (int i = Option; i<Math.min(Option + option , BinaryFile.length); i++) {
					
				}
			}
				
				
			
		}
		
		//break;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

