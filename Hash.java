import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Hash {
    
    public static void main(String[] args) {
        final int SIZE = 128;
        String[] hashTable = new String[SIZE];
        int[] probeCount = new int[SIZE]; // Array to store probe counts for each element
        int result;
        String filePath = "output.csv";
        File file = new File("input.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result = Math.abs((line.charAt(1) + ((line.charAt(3) + line.charAt(4) + line.charAt(6) + line.charAt(7)) / 381 + line.charAt(0)) / 587 - line.charAt(10))) % SIZE;
                
                int startIndex = result;
                int countProbes = 1;
                while (hashTable[result] != null) {
                    result = (result + 1) % SIZE;
                    countProbes++; 
                    if (result == startIndex) {
                        System.out.println("Hash table is full, unable to insert more items.");
                        return; 
                    }
                }
                hashTable[result] = line;
                probeCount[result] = countProbes; 
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
            return;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {  
            writer.println("Index, Item, Probes,");
   
            for (int i = 0; i < SIZE; i++) {
                if (hashTable[i] != null) {
                    writer.printf("%3d,%-16s,%d\n", i, hashTable[i], probeCount[i]);
                    System.out.printf("Index %3d: %-16s, Probes: %d\n", i, hashTable[i], probeCount[i]);
        }
    }
}
        catch (IOException e) {
         e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i<SIZE; i++){
            sum = sum + probeCount[i];
        }
        int max = probeCount[0];
        for (int i = 1; i <SIZE; i++){
            if (probeCount[i] > max){
                max = probeCount[i];
            }   
        }

        double average = sum/SIZE;
        System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");
    }
}
