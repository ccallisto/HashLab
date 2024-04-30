import java.io.IOException;
import java.io.RandomAccessFile;

public class Hash {
    final static int SIZE = 128;

    public static void main(String[] args) {
        String inputPath = "input.txt";
        String outputPath = "output.csv";

        hash1(inputPath, outputPath); // burris hash linear
        // hash2(inputPath, outputPath); // my hash linear
        // hash3(inputPath, outputPath); // burris hash random
        // hash4(inputPath, outputPath); // my hash random
    }

    public static void hash1(String inputPath, String outputPath) {
        burrHash burrisHash = new burrHash();
        try (RandomAccessFile inputFile = new RandomAccessFile(inputPath, "r");
             RandomAccessFile outputFile = new RandomAccessFile(outputPath, "rw")) {

            String line;
            while ((line = inputFile.readLine()) != null) {
                burrisHash.insert(line);
            }

            outputFile.writeBytes("Index, Item, Probes, Initial\n");

            for (int i = 0; i < SIZE; i++) {
                if (burrisHash.getHashVal(i) != null) {
                    String outputLine = String.format("%3d,%-16s,%d,%d\n", i, burrisHash.getHashVal(i), burrisHash.getProbes(i), burrisHash.getInit(i));
                    outputFile.writeBytes(outputLine);
                    System.out.printf("Index %3d: %-16s, Probes: %d, InitialProbe: %d\n", i, burrisHash.getHashVal(i), burrisHash.getProbes(i), burrisHash.getInit(i));
                }
            }

            int sum = 0, max = burrisHash.getProbes(0);
            for (int i = 0; i < SIZE; i++) {
                sum += burrisHash.getProbes(i);
                if (burrisHash.getProbes(i) > max) {
                    max = burrisHash.getProbes(i);
                }
            }
            double average = sum / (double) 100;
            System.out.println("Expected probes: " + average + "  Max probes: " + max + "  Min probes: 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hash2(String inputPath, String outputPath) {
        myHash myHash = new myHash();
        try (RandomAccessFile inputFile = new RandomAccessFile(inputPath, "r");
             RandomAccessFile outputFile = new RandomAccessFile(outputPath, "rw")) {

            String line;
            while ((line = inputFile.readLine()) != null) {
                myHash.insert(line);
            }

            outputFile.writeBytes("Index, Item, Probes, Initial\n");

            for (int i = 0; i < SIZE; i++) {
                if (myHash.getHashVal(i) != null) {
                    String outputLine = String.format("%3d,%-16s,%d,%d\n", i, myHash.getHashVal(i), myHash.getProbes(i), myHash.getInit(i));
                    outputFile.writeBytes(outputLine);
                    System.out.printf("Index %3d: %-16s, Probes: %d, InitialProbe: %d\n", i, myHash.getHashVal(i), myHash.getProbes(i), myHash.getInit(i));
                }
            }

            int sum = 0, max = myHash.getProbes(0);
            for (int i = 0; i < SIZE; i++) {
                sum += myHash.getProbes(i);
                if (myHash.getProbes(i) > max) {
                    max = myHash.getProbes(i);
                }
            }
            double average = sum / (double) 100;
            System.out.println("Expected probes: " + average + "  Max probes: " + max + "  Min probes: 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hash3(String inputPath, String outputPath) {
        RandomBurrHash rburrisHash = new RandomBurrHash();
        try (RandomAccessFile inputFile = new RandomAccessFile(inputPath, "r");
             RandomAccessFile outputFile = new RandomAccessFile(outputPath, "rw")) {

            String line;
            while ((line = inputFile.readLine()) != null) {
                rburrisHash.insert(line);
            }

            outputFile.writeBytes("Index, Item, Probes, Initial\n");

            for (int i = 0; i < SIZE; i++) {
                if (rburrisHash.getHashVal(i) != null) {
                    String outputLine = String.format("%3d,%-16s,%d,%d\n", i, rburrisHash.getHashVal(i), rburrisHash.getProbes(i), rburrisHash.getInit(i));
                    outputFile.writeBytes(outputLine);
                    System.out.printf("Index %3d: %-16s, Probes: %d, InitialProbe: %d\n", i, rburrisHash.getHashVal(i), rburrisHash.getProbes(i), rburrisHash.getInit(i));
                }
            }

            int sum = 0, max = rburrisHash.getProbes(0);
            for (int i = 0; i < SIZE; i++) {
                sum += rburrisHash.getProbes(i);
                if (rburrisHash.getProbes(i) > max) {
                    max = rburrisHash.getProbes(i);
                }
            }
            double average = sum / (double)100;
            System.out.println("Expected probes: " + average + "  Max probes: " + max + "  Min probes: 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void hash4(String inputPath, String outputPath) {
        myRandHash myRandHash = new myRandHash();
        try (RandomAccessFile inputFile = new RandomAccessFile(inputPath, "r");
             RandomAccessFile outputFile = new RandomAccessFile(outputPath, "rw")) {

            String line;
            while ((line = inputFile.readLine()) != null) {
                myRandHash.insert(line);
            }

            outputFile.writeBytes("Index, Item, Probes, Initial\n");

            for (int i = 0; i < SIZE; i++) {
                if (myRandHash.getHashVal(i) != null) {
                    String outputLine = String.format("%3d,%-16s,%d,%d\n", i, myRandHash.getHashVal(i), myRandHash.getProbes(i), myRandHash.getInit(i));
                    outputFile.writeBytes(outputLine);
                    System.out.printf("Index %3d: %-16s, Probes: %d, InitialProbe: %d\n", i, myRandHash.getHashVal(i), myRandHash.getProbes(i), myRandHash.getInit(i));
                }
            }

            int sum = 0, max = myRandHash.getProbes(0);
            for (int i = 0; i < SIZE; i++) {
                sum += myRandHash.getProbes(i);
                if (myRandHash.getProbes(i) > max) {
                    max = myRandHash.getProbes(i);
                }
            }
            double average = sum / (double) 100;
            System.out.println("Expected probes: " + average + "  Max probes: " + max + "  Min probes: 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
