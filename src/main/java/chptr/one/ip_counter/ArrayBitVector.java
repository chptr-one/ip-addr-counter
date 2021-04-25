package chptr.one.ip_counter;

/**
 * This class implements a {@code BitVector} of a fixed capacity.
 * Capacity is limited from above 2^32 bits that consumes 512 MB of memory.
 * The minimum capacity is 1 bit so the creation of an empty vector is not provided.
 *
 * <p>Each {@code FixedSizeBitVector} has a sufficient size for storage exactly {@code capacity} bits.
 * Bits are available on unique indexes in the range from 0 to capacity-1.
 *
 * <p>When creating a vector, all bits are initially sets to {@code false}.
 * For any bit there are two operations: set it in {@code true} and examine its value.
 * For the entire bit vector you can receive its cardinality, that is, the number of bits sets in the {@code true}.
 *
 * <p>All methods of this class will throw an {@code IllegalArgumentException} in case of incorrect arguments.
 *
 * <p>This class is not safe for multithreaded use without external synchronization.
 */

public class ArrayBitVector implements BitVector {

    public static final long MIN_CAPACITY = 1L;
    public static final long MAX_CAPACITY = 1L << 32;

    private final long capacity;
    private final int[] intArray;
    private long cardinality;

    /**
     * Creates a bit vector is large enough to storage exactly {@code capacity} bits.
     *
     * @param capacity capacity in range 1..MAX_CAPACITY inclusive.
     * @throws IllegalArgumentException in case {@code capacity} out of bounds.
     */
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

    @Override
    public void setBit(long bitIndex) {
        checkBounds(bitIndex);
        int index = (int) (bitIndex >> 5);
        int bit = 1 << (bitIndex & 31);
        if ((intArray[index] & bit) == 0) {
            cardinality++;
            intArray[index] |= bit;
        }
    }

    @Override
    public boolean getBit(long bitIndex) {
        checkBounds(bitIndex);
        int index = (int) (bitIndex >> 5);
        int bit = 1 << (bitIndex & 31);
        return (intArray[index] & bit) != 0;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    @Override
    public long getCardinality() {
        return cardinality;
    }
}
