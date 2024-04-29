public class burrHash{
    final int SIZE = 128;
    String[] hashTable = new String[SIZE];
    int result;
    int[] probeCount = new int[SIZE];
    int[] initialResult = new int[SIZE];

    
    private char safeCharAt(String str, int index) {
        if (str.charAt(index) == ' ') {
            return 0; // Return 0 if the index is out of bounds
        }
        return str.charAt(index);
    }

    public void insert(String line) {
        int hash = safeCharAt(line, 1) 
            + ((safeCharAt(line, 3) + safeCharAt(line, 4) 
                + safeCharAt(line, 6) + safeCharAt(line, 7)) / 381 
            + safeCharAt(line, 0)) / 587 
            - (safeCharAt(line, 10));
        result = Math.abs(hash) % SIZE;
        
        int startIndex = result;
        int countProbes = 1;
        while (hashTable[result] != null) {
            result = (result + 1) % SIZE;
            countProbes++;
            if (result == startIndex) {
                System.out.println("Hash table is full, unable to insert more items.");
                return; // Exit the method if the table is full
            }
        }
        hashTable[result] = line;
        probeCount[result] = countProbes;
        initialResult[result] = startIndex;

    }

    public String getHashVal(int i){

        return hashTable[i];
    }

    public int getProbes(int i){
        return probeCount[i];
    }
    public int getInit(int i){
        return initialResult[i];
    }
}