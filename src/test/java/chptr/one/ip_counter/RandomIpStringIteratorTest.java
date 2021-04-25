package chptr.one.ip_counter;

import chptr.one.ip_counter.generator.RandomIpStringIterator;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class RandomIpStringIteratorTest {

    @Test
    void expectException_capacityLessThenOne() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomIpStringIterator(0, 1, Collections.emptySet()));
    }

    @Test
    void expectException_uniqueLessThenOne() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomIpStringIterator(1, 0, Collections.emptySet()));
    }

    @Test
    void expectException_capacityLessThenUnique() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomIpStringIterator(10, 20, Collections.emptySet()));
    }

    @Test
    void expectException_uniqueLessThenSizeOfRequired() {
        assertThrows(IllegalArgumentException.class,
                () -> new RandomIpStringIterator(20, 1, Set.of("0.0.0.0", "1.1.1.1")));
    }

    @Test
    void expectException_requiredIsNull() {
        assertThrows(NullPointerException.class,
                () -> new RandomIpStringIterator(20, 1, null));
    }

    private List<String> generateIpList(int capacity, int unique, Set<String> required) {
        Iterable<String> iterable = () -> new RandomIpStringIterator(capacity, unique, required);
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Test
    void expectSameString_uniqueIsOne() {
        var iterator = new RandomIpStringIterator(10, 1, Collections.emptySet());
        Set<String> set = new HashSet<>();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        assertEquals(1, set.size());
    }

    @Test
    void iteratorHasRightCapacity() {
        var list = generateIpList(10, 10, Collections.emptySet());
        assertEquals(10, list.size());
    }

    @Test
    void iteratorContainsRightAmountOfUniqueIp() {
        var list = generateIpList(20, 10, Collections.emptySet());
        long actualUnique = list.stream()
                .distinct()
                .count();
        assertEquals(10L, actualUnique);
    }

    @Test
    void iteratorContainsAllRequiredIp() {
        Set<String> required = Set.of("0.0.0.0", "1.1.1.1", "255.255.255.255");
        var list = generateIpList(10, 3, required);
        assertTrue(list.containsAll(required));
    }
}