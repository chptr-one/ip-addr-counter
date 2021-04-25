package chptr.one.ip_counter;

public class ArrayBitVector {

    public static final long MIN_CAPACITY = 1L;
    public static final long MAX_CAPACITY = 1L << 32;

    private final long capacity;
    private final int[] intArray;
    private long cardinality;

    public ArrayBitVector(long capacity) {
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("Capacity must be in range [1.." + MAX_CAPACITY + "].");
        }
        int arraySize = 1 + (int) ((capacity - 1) >> 5);
        this.intArray = new int[arraySize];
        this.capacity = capacity;
    }

    private void checkBounds(long bitIndex) {
        if (bitIndex < 0 || bitIndex >= capacity) {
            throw new IllegalArgumentException("Bit index must be in range [0.." + (capacity - 1) + "].");
        }
    }

    public void setBit(long bitIndex) {
        checkBounds(bitIndex);
        int index = (int) (bitIndex >> 5);
        int bit = 1 << (bitIndex & 31);
        if ((intArray[index] & bit) == 0) {
            cardinality++;
            intArray[index] |= bit;
        }
    }

    public boolean getBit(long bitIndex) {
        checkBounds(bitIndex);
        int index = (int) (bitIndex >> 5);
        int bit = 1 << (bitIndex & 31);
        return (intArray[index] & bit) != 0;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getCardinality() {
        return cardinality;
    }
}
