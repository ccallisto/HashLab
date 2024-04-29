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

    hash1(file, filePath); //burris hash linear
   hash2(file, filePath); //my hash linear
   hash3(file, filePath); //burris hash rando
   hash4(file, filePath); //my hash random
    }


    public static void hash1(File file, String filePath) {
        burrHash burrisHash = new burrHash();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                burrisHash.insert(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Index, Item, Probes,");

            for (int i = 0; i < SIZE; i++) {
                if (burrisHash.getHashVal(i) != null) {
                    writer.printf("%3d,%-16s,%d\n", i, burrisHash.getHashVal(i), burrisHash.getProbes(i));
                    System.out.printf("Index %3d: %-16s, Probes: %d,   InitialProbe:  %d\n", i, burrisHash.getHashVal(i), burrisHash.getProbes(i), burrisHash.getInit(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum = sum + burrisHash.getProbes(i);
        }
        int max = burrisHash.getProbes(0);
        for (int i = 1; i < SIZE; i++) {
            if (burrisHash.getProbes(i) > max) {
                max = burrisHash.getProbes(i);
            }
        }

        double average = sum / (double)100;
        System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");


    }

    public static void hash2(File file, String filePath) {
        myHash myHash = new myHash();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                myHash.insert(line);
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Index, Item, Probes,");

            for (int i = 0; i < SIZE; i++) {
                if (myHash.getHashVal(i) != null) {
                    writer.printf("%3d,%-16s,%d\n", i, myHash.getHashVal(i), myHash.getProbes(i));
                    System.out.printf("Index %3d: %-16s, Probes: %d,   InitialProbe: %d\n",  i, myHash.getHashVal(i), myHash.getProbes(i), myHash.getInit(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum = sum + myHash.getProbes(i);
        }
        int max = myHash.getProbes(0);
        for (int i = 1; i < SIZE; i++) {
            if (myHash.getProbes(i) > max) {
                max = myHash.getProbes(i);
            }
        }

        double average = sum / (double)100;
        System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");


    }

    public static void hash3(File file, String filePath) {
        RandomBurrHash rburrisHash = new RandomBurrHash();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                rburrisHash.insert(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Index, Item, Probes,");

            for (int i = 0; i < SIZE; i++) {
                if (rburrisHash.getHashVal(i) != null) {
                    writer.printf("%3d,%-16s,%d\n", i, rburrisHash.getHashVal(i), rburrisHash.getProbes(i));
                    System.out.printf("Index %3d: %-16s, Probes: %d,  Initial Probe:  %d\n", i, rburrisHash.getHashVal(i), rburrisHash.getProbes(i), rburrisHash.getInit(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum = sum + rburrisHash.getProbes(i);
        }
        int max = rburrisHash.getProbes(0);
        for (int i = 1; i < SIZE; i++) {
            if (rburrisHash.getProbes(i) > max) {
                max = rburrisHash.getProbes(i);
            }
        }

        double average = sum / (double)100;
        System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");
    }

    public static void hash4(File file, String filePath) {
        myRandHash myRandHash = new myRandHash();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                myRandHash.insert(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Index, Item, Probes,");

            for (int i = 0; i < SIZE; i++) {
                if (myRandHash.getHashVal(i) != null) {
                    writer.printf("%3d,%-16s,%d\n", i, myRandHash.getHashVal(i), myRandHash.getProbes(i));
                    System.out.printf("Index %3d: %-16s, Probes: %d,  Initial Probe: %d\n", i, myRandHash.getHashVal(i), myRandHash.getProbes(i), myRandHash.getInit(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            sum = sum + myRandHash.getProbes(i);
        }
        int max = myRandHash.getProbes(0);
        for (int i = 1; i < SIZE; i++) {
            if (myRandHash.getProbes(i) > max) {
                max = myRandHash.getProbes(i);
            }
        }

        double average = sum / (double)100;
        System.out.println("expected probes: " + average + "  max probes: " + max + "  min probes: " + "1");

    }
}



