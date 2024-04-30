public class myHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

    private int leftCircularShift(int n, int d) {
        return (n << d) | (n >>> (8 - d));  
    }

    private char safeCharAt(String str, int index) {
        if (index >= str.length() || str.charAt(index) == ' ') {
            return 0;
        }
        return str.charAt(index);
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
            result = (result + 1) % SIZE;
            countProbes++;
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
