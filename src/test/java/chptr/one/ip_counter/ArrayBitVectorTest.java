package chptr.one.ip_counter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static chptr.one.ip_counter.ArrayBitVector.MAX_CAPACITY;
import static chptr.one.ip_counter.ArrayBitVector.MIN_CAPACITY;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBitVectorTest {

    @Test
    void expectException_createBitArrayCapacityLessThenMin() {
        assertThrows(IllegalArgumentException.class,
                () -> new ArrayBitVector(MIN_CAPACITY - 1));
    }

    @Test
    void expectException_createBitArrayCapacityGreaterThanMax() {
        assertThrows(IllegalArgumentException.class,
                () -> new ArrayBitVector(MAX_CAPACITY + 1));
    }

    @Test
    void notExpectException_createBitArrayMinCapacity() {
        assertDoesNotThrow(() -> new ArrayBitVector(MIN_CAPACITY));
    }

    @Test
    void notExpectException_createBitArrayMaxCapacity() {
        assertDoesNotThrow(() -> new ArrayBitVector(MAX_CAPACITY));
    }

    @Test
    void bitArrayHasRightBitCapacity() {
        Map<Long, Long> testCases = Map.of(
                MIN_CAPACITY, 1L,
                31L, 31L,
                32L, 32L,
                33L, 33L,
                MAX_CAPACITY, MAX_CAPACITY);

        for (var testCase : testCases.entrySet()) {
            BitVector bitVector = new ArrayBitVector(testCase.getKey());
            assertEquals(testCase.getValue(), bitVector.getCapacity());
        }
    }

    @Test
    void expectException_bitIndexLessThenZero() {
        BitVector bitVector = new ArrayBitVector(MIN_CAPACITY);
        assertThrows(IllegalArgumentException.class,
                () -> bitVector.setBit(-1));
    }

    @Test
    void expectException_bitIndexExceededBitCapacity() {
        BitVector bitVector = new ArrayBitVector(MAX_CAPACITY);
        assertThrows(IllegalArgumentException.class,
                () -> bitVector.setBit(MAX_CAPACITY));
    }

    @Test
    void setZeroBit() {
        BitVector bitVector = new ArrayBitVector(1L);
        bitVector.setBit(0);
        assertTrue(bitVector.getBit(0));
    }

    @Test
    void setLastBit() {
        BitVector bitVector = new ArrayBitVector(MAX_CAPACITY);
        bitVector.setBit(MAX_CAPACITY - 1);
        assertTrue(bitVector.getBit(MAX_CAPACITY - 1));
    }

    @Test
    void cardinalityReturnsRightValue() {
        BitVector bitVector = new ArrayBitVector(1L);
        assertEquals(0L, bitVector.getCardinality());
        bitVector.setBit(0L);
        assertEquals(1L, bitVector.getCardinality());
    }
}