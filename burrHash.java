public class burrHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

    private char safeCharAt(String str, int index) {
        if (str.charAt(index) == ' ') {
            return 0; 
        }
        return str.charAt(index);
    }



    public void insert(String line) {
        int hash = safeCharAt(line, 1) 
            + ((safeCharAt(line, 3) + safeCharAt(line, 4) 
                + safeCharAt(line, 6) + safeCharAt(line, 7)) / 381 
            + safeCharAt(line, 0)) / 587 
            - (safeCharAt(line, 10));
        int result = Math.abs(hash) % SIZE;
        
        int startIndex = result;
        int countProbes = 1;
        while (hashTable[result] != null) {
            result = (result + 1) % SIZE;
            countProbes++;
        }
        hashTable[result] = new HashEntry(line, countProbes, startIndex);
    }

    public HashEntry getHashEntry(int i) {
        return hashTable[i];
    }

    public String getHashVal(int i) {
        HashEntry entry = getHashEntry(i);
        return entry != null ? entry.line() : null;
    }

    public int getProbes(int i) {
        HashEntry entry = getHashEntry(i);
        return entry != null ? entry.probeCount() : 0;
    }

    public int getInit(int i) {
        HashEntry entry = getHashEntry(i);
        return entry != null ? entry.initialIndex() : 0;
    }
}
