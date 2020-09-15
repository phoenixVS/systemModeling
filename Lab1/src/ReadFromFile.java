import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;  // Import the IOException class to handle errors

public class ReadFromFile {
    public static void main(int type) {
        try {
            String filename = "firstWay.txt";
            switch(type) {
                case 1: filename = "firstWay.txt"; break;
                case 2: filename = "secondWay.txt"; break;
                case 3: filename = "thirdWay.txt"; break;
            }
            BufferedReader br = new BufferedReader(new FileReader(filename));
            for (int i = 0; i < 10000; i++) {
                System.out.println(br.readLine());
            }
            br.close();
            System.out.println("Successfully read from file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
