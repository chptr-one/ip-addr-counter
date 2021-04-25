package chptr.one.ip_counter;

public interface BitVector {
    /**
     * Sets a specific bit in {@code true} iff the bit was set in {@code false}.
     *
     * @param bitIndex index of specific bit.
     * @throws IllegalArgumentException in case {@code bitIndex} out of bounds.
     */
    void setBit(long bitIndex);

    /**
     * Returns the value of the bit with the specified index.
     * The value is {@code true} if the bit with the index bitIndex is currently set;
     * otherwise, the result is {@code false}.
     *
     * @param bitIndex index of specific bit.
     * @return the value of the bit with the specified index.
     * @throws IllegalArgumentException in case {@code bitIndex} out of bounds.
     */
    boolean getBit(long bitIndex);

    /**
     * Returns current capacity of this bit vector.
     *
     * @return capacity of this bit vector.
     */
    long getCapacity();

    /**
     * Returns cardinality of this bit vector.
     *
     * @return the number of bits set to true in this bit vector.
     */
    long getCardinality();
}
