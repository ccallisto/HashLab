import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
//this is going to be burris's hash just with random probing
public class RandomHash{
    public static void main(String[] args) {

        final int SIZE = 128;
        String[] hashTable = new String[SIZE];
        int[] probeCount = new int[SIZE]; // Array to store probe counts for each element
        int result;
        
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

        for (int i = 0; i < SIZE; i++) {
            if (hashTable[i] != null) {
                System.out.printf("Index %3d: %-16s, Probes: %d\n", i, hashTable[i], probeCount[i]);


            }
        }
        int sum = 0;
        for (int i = 0; i<SIZE; i++){
            sum = sum + probeCount[i];
        }
        int average = sum/SIZE;
        System.out.println("expected probes: " + average);
    }

}