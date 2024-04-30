public class burrHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

    private char safeCharAt(String str, int index) {
        if (index >= str.length() || str.charAt(index) == ' ') {
            return '0'; // Return '0' if the index is out of bounds or the character is a space
        }
        return str.charAt(index);
    }

  private int concatenateDigits(char a, char b) {
        String asciiConcatenation = "" + ((int) a) + ((int) b);
        return Integer.parseInt(asciiConcatenation);
    }  

    public void insert(String line) {
        // Ensure that characters at positions 3 and 4 are digits or treated as '0'
        char char3 = safeCharAt(line, 3);
        char char4 = safeCharAt(line, 4);
        char char6 = safeCharAt(line, 6);
        char char7 = safeCharAt(line, 7);
        int concatenatedValue34 = concatenateDigits(char3, char4);
        int concatenatedValue67 = concatenateDigits(char6, char7);
        // Compute the hash using the modified value
        int hash = safeCharAt(line, 1) 
            + (concatenatedValue34 + concatenatedValue67) / 381 
            + safeCharAt(line, 0) / 587 
            - safeCharAt(line, 10);
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
