import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class WriteToFile {
    public static void main(String[] args, int type) {
        try {
            String filename = "firstWay.txt";
            switch(type) {
                case 1: filename = "firstWay.txt"; break;
                case 2: filename = "secondWay.txt"; break;
                case 3: filename = "thirdWay.txt"; break;
            }
            FileWriter myWriter = new FileWriter(filename);
            for (int i = 0; i < args.length; i++) {
                myWriter.write(args[i]);
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
