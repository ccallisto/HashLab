public class myHash {
    final int SIZE = 128;
    String[] hashTable = new String[SIZE];
    int result;
    int[] probeCount = new int[SIZE];

    // barrel shifter function
    private int leftCircularShift(int n, int d) {
        return (n << d) | (n >>> (32 - d));
    }
    private char safeCharAt(String str, int index) {
        if (str.charAt(index) == ' ') {
            return 0; // Return 0 if the index is out of bounds
        }
        return str.charAt(index);
    }
    //barrel shifter hash algo
    public void insert(String line) {
        int hash = 0;
        for (int i = 0; i < line.length(); i++) {
            int shifted = leftCircularShift(safeCharAt(line, i), i % 32);
            hash = (hash + shifted) % SIZE;
        }
        result = Math.abs(hash) % SIZE;

        int countProbes = 1;
        int startIndex = result;
        while (hashTable[result] != null) {
            result = (result + 1) % SIZE;
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
