import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Hash {
    final static int SIZE = 128;
    public static void main(String[] args) {

        String filePath = "output.csv";
        File file = new File("input.txt");
        hash1(file, filePath);
        // hash2(file, filePath);
    }



    public static void hash1(File file, String filePath){
        burrHash burrisHash = new burrHash();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                burrisHash.insert(line);
                }
            }
         catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {  
            writer.println("Index, Item, Probes,");
   
            for (int i = 0; i < SIZE; i++) {
                if (burrisHash.getHashVal(i) != null) {
                    writer.printf("%3d,%-16s,%d\n", i, burrisHash.getHashVal(i), burrisHash.getProbes(i));
                    System.out.printf("Index %3d: %-16s, Probes: %d\n", i, burrisHash.getHashVal(i), burrisHash.getProbes(i));
        }
    }
}
        catch (IOException e) {
         e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i<SIZE; i++){
            sum = sum + burrisHash.getProbes(i);
        }
        int max = burrisHash.getProbes(0);
        for (int i = 1; i <SIZE; i++){
            if (burrisHash.getProbes(i) > max){
                max = burrisHash.getProbes(i);
            }   
        }

        double average = sum/SIZE;
        System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");
    
    
        }
        public static void hash2(File file, String filePath){
            myHash myHash = new myHash();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    myHash.insert(line);
                    }
                }
             catch (FileNotFoundException e) {
                System.out.println("Failed to open file.");
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {  
                writer.println("Index, Item, Probes,");
       
                for (int i = 0; i < SIZE; i++) {
                    if (myHash.getHashVal(i) != null) {
                        writer.printf("%3d,%-16s,%d\n", i, myHash.getHashVal(i), myHash.getProbes(i));
                        System.out.printf("Index %3d: %-16s, Probes: %d\n", i, myHash.getHashVal(i), myHash.getProbes(i));
            }
        }
    }
            catch (IOException e) {
             e.printStackTrace();
            }
            int sum = 0;
            for (int i = 0; i<SIZE; i++){
                sum = sum + myHash.getProbes(i);
            }
            int max = myHash.getProbes(0);
            for (int i = 1; i <SIZE; i++){
                if (myHash.getProbes(i) > max){
                    max = myHash.getProbes(i);
                }   
            }
    
            double average = sum/SIZE;
            System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");
        
        
            }
    }



