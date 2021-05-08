package chptr.one.ip_counter;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.ToLongFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IpStringHashFunctionTest {

    ToLongFunction<String> hashFunction = new IpStringHashFunction();
    Map<String, Long> cornerCases = Map.of(
            "0.0.0.0", 0L,
            "255.255.255.255", (1L << 32) - 1
    );

    @Test
    void doNotAllowNullArgument() {
        assertThrows(NullPointerException.class, () -> hashFunction.applyAsLong(null));
    }

    @Test
    void throwsRuntimeExceptionOnInvalidAddress() {
        assertThrows(RuntimeException.class, () -> hashFunction.applyAsLong("0.0.0.256"));
    }

    @Test
    void cornerCases() {
        for (var testCase : cornerCases.entrySet()) {
            long actual = hashFunction.applyAsLong(testCase.getKey());
            long expected = testCase.getValue();
            assertEquals(expected, actual);
        }
    }
}