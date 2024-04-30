public class burrHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];

    private record HashEntry(String line, int probeCount, int initialIndex) {}

    private char safeCharAt(String str, int index) {
        if (index >= str.length() || str.charAt(index) == ' ') {
            return 0; 
        }
        return str.charAt(index);
    }

   private int concatenateDigits(char a, char b) {
        String asciiConcatenation = "" + ((int) a) + ((int) b);
        return Integer.parseInt(asciiConcatenation);
    }  

    public void insert(String line) {
        char char0 = safeCharAt(line, 0);
        char char1 = safeCharAt(line, 1);
        char char3 = safeCharAt(line, 3);
        char char4 = safeCharAt(line, 4);
        char char6 = safeCharAt(line, 6);
        char char7 = safeCharAt(line, 7);
        int c34 = concatenateDigits(char3, char4);
        int c67 = concatenateDigits(char6, char7);
        int c00 = concatenateDigits(char0, char0);
        int c11 = concatenateDigits(char1, char1);
        
        //all characters in your hash function were shifted by 1 digit due to java arrays beginning at 0


        int hash = c11 + ((c34 + c67) / 381 + c00 )/ 587 - safeCharAt(line, 10);
        int result = Math.abs(hash) % SIZE;
        int countProbes =1;
        int startIndex = result;
        
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
