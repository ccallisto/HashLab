public class rNumGen {
    final private int numBits;
    private int r = 1;

    public rNumGen(int numBits) {
        this.numBits = numBits;
    }

    public void initialRandInteger() {
        r = 1;
    }

    public int uniqueRandInteger() {
        r = (5 * r) % (1 << (numBits + 2));
        return r / 4;
    }
}