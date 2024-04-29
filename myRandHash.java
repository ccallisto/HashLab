public class myRandHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

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
            int shifted = leftCircularShift(safeCharAt(line, i), i % 32);
            hash = (hash + shifted) % SIZE;
        }
        int result = Math.abs(hash) % SIZE;
        int initialRes = result;
        int countProbes = 1;
        while (hashTable[result] != null) {
            result = (result + randomGenerator.uniqueRandInteger()) % SIZE; // Use uniqueRandInteger for random probing
            countProbes++;
            if (result == initialRes) { // Add check for circular probing preventing infinite loop
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
