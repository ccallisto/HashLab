public class RandomBurrHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

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
        int countProbes = 1;
        int initialRes = result;

        while (hashTable[result] != null) {
            result = (result + randomGenerator.uniqueRandInteger()) % SIZE; // Use uniqueRandInteger for the probe step
            countProbes++;
            if (result == initialRes) { // Check for a full table should be added here ideally
                System.out.println("Hash table is full, unable to insert more items.");
                return;
            }
        }

        hashTable[result] = new HashEntry(line, countProbes, initialRes);
    }

    public String getHashVal(int i) {
        HashEntry entry = hashTable[i];
        return entry != null ? entry.line() : null;
    }

    public int getProbes(int i) {
        HashEntry entry = hashTable[i];
        return entry != null ? entry.probeCount() : 0;
    }

    public int getInit(int i) {
        HashEntry entry = hashTable[i];
        return entry != null ? entry.initialIndex() : 0;
    }
}
