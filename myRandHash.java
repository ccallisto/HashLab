public class myRandHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

    rNumGen randomGenerator = new rNumGen(7); // Assuming this is a class that generates unique random integers

    private char safeCharAt(String str, int index) {
        if (index >= str.length() || str.charAt(index) == ' ') {
            return 0;
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
            result = (result + randomGenerator.uniqueRandInteger()) % SIZE;
            countProbes++;
            if (result == initialRes) { // Avoid infinite loops in full hash table scenario
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
