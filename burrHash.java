public class burrHash{
    final int SIZE = 128;
    String[] hashTable = new String[SIZE];
    int result;
    int[] probeCount = new int[SIZE];
    
    
    public void insert(String line){
        result = Math.abs((line.charAt(1) + ((line.charAt(3) + line.charAt(4) + line.charAt(6) + line.charAt(7)) / 381 + line.charAt(0)) / 587 - line.charAt(10))) % SIZE;
        int countProbes;  
        int startIndex = result;
        countProbes = 1;
        while (hashTable[result] != null) {
            result = (result + 1) % SIZE;
            countProbes++; 
            if (result == startIndex) {
                System.out.println("Hash table is full, unable to insert more items.");
            }
        }
        hashTable[result] = line;
        probeCount[result] = countProbes; 

    }
    public String getHashVal(int i){

        return hashTable[i];
    }

    public int getProbes(int i){
        return probeCount[i];
    }
}