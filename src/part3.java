import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class part3 {
    public static void main(String[] args) {
        BufferedReader br = null;
        String[] BinaryFile = { "Basketball.csv.ser", "Football.csv.ser", "Hokey.csv.ser" }; // array of files to read

        // a for loop to deserialize the objects in the binary files
        for (int i = 0; i < BinaryFile.length; i++) {
            try {
                FileInputStream fileIn = new FileInputStream(BinaryFile[i]);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                in.close();
                fileIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Interactive code that will navigate the user depending on their choices
        Scanner key = new Scanner(System.in);
        String input = "";
        String option = "";
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
                System.out.println("1" + "" + BinaryFile[0] + "(" + (BinaryFile[0] + 1) + "records");
                System.out.println("2" + "" + BinaryFile[1] + "(" + (BinaryFile[1] + 1) + "records");
                System.out.println("3" + "" + BinaryFile[2] + "(" + (BinaryFile[2] + 1) + "records");
                System.out.println("4" + "" + "Exit");
            }
        }

    }
}
