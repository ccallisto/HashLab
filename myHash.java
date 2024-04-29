public class myHash {
    final int SIZE = 128;
    HashEntry[] hashTable = new HashEntry[SIZE];  //initialized to be modified at the end of the operation

    private record HashEntry(String line, int probeCount, int initialIndex) {}

    private int leftCircularShift(int n, int d) {
        return (n << d) | (n >>> (32 - d));
    }

    private char safeCharAt(String str, int index) {
        if (str.charAt(index) == ' ') {
            return 0;
        }
        return str.charAt(index);
    }

    public void insert(String line) {
        HashEntry[] tempHashTable = new HashEntry[SIZE];
        //effectively i have verified that the hash table will not run into errors before commiting it to the real table
        System.arraycopy(hashTable, 0, tempHashTable, 0, SIZE);
        int hash = 0;
        for (int i = 0; i < line.length(); i++) {
            int shifted = leftCircularShift(safeCharAt(line, i), i % 32);
            hash = (hash + shifted) % SIZE;
        }
        int result = Math.abs(hash) % SIZE;
        int initialRes = result;
        int countProbes = 1;
        while (tempHashTable[result] != null) {
            result = (result + 1) % SIZE;
            countProbes++;

        }
        tempHashTable[result] = new HashEntry(line, countProbes, initialRes); 
        for (int i = 0; i<SIZE ; i++){
            hashTable[i] = tempHashTable[i];
        }
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
