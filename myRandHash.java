public class myRandHash {
    final int SIZE = 128;
    String[] hashTable = new String[SIZE];
    int[] probeCount = new int[SIZE];
    rNumGen randomGenerator = new rNumGen(7); // Assuming a suitable number of bits for the table size
    private char safeCharAt(String str, int index) {
        if (str.charAt(index) == ' ') {
            return 0; // Return 0 if the index is out of bounds
        }
        return str.charAt(index);
    }

    private int leftCircularShift(int n, int d) {
        return (n << d) | (n >>> (32 - d));
    }

    public void insert(String line) {
        int hash = 0;
        for (int i = 0; i < line.length(); i++) {
        int shifted = leftCircularShift(safeCharAt(line, i), i % 32);            hash = (hash + shifted) % SIZE;
        }
        int result = Math.abs(hash) % SIZE;

        int countProbes = 1;
        int startIndex = result;
        while (hashTable[result] != null) {
            result = (result + randomGenerator.uniqueRandInteger()) % SIZE; // Use uniqueRandInteger for random probing
            countProbes++;
            if (result == startIndex) {
                System.out.println("Hash table is full, unable to insert more items.");
                return;
            }
        }
        hashTable[result] = line;
        probeCount[result] = countProbes;
    }

    public String getHashVal(int i) {
        return hashTable[i];
    }

    public int getProbes(int i) {
        return probeCount[i];
    }
}
