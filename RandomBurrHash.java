public class RandomBurrHash {
    final int SIZE = 128;
    String[] hashTable = new String[SIZE];
    int[] probeCount = new int[SIZE];
    rNumGen randomGenerator = new rNumGen(7);
    private char safeCharAt(String str, int index) {
        if (str.charAt(index) == ' ') {
            return 0; // Return 0 if the index is out of bounds
        }
        return str.charAt(index);
    }
    public RandomBurrHash() {
        randomGenerator.initialRandInteger();
    }

    public void insert(String line) {
        int result = Math.abs((safeCharAt(line, 1) + ((safeCharAt(line, 3) + safeCharAt(line, 4) + safeCharAt(line, 6) + safeCharAt(line, 7)) / 381 + safeCharAt(line, 0)) / 587 - safeCharAt(line, 10))) % SIZE;
        int startIndex = result;
        int countProbes = 1;
        while (hashTable[result] != null) {
            result = (result + randomGenerator.uniqueRandInteger()) % SIZE; // Use uniqueRandInteger for the probe step
            countProbes++;
            if (result == startIndex) {
                System.out.println("Hash table is full, unable to insert more items.");
                break; // Added break to prevent endless loop if table is full
            }
        }
        if (hashTable[result] == null) {
            hashTable[result] = line;
            probeCount[result] = countProbes;
        }
    }

    public String getHashVal(int i) {
        return hashTable[i];
    }

    public int getProbes(int i) {
        return probeCount[i];
    }
}