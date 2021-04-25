package chptr.one.ip_counter.generator;

import java.util.*;

public class RandomIpStringIterator implements Iterator<String> {
    private final int capacity;
    private final Set<String> uniqueIpStrings;
    private Iterator<String> uniqueIterator;

    public RandomIpStringIterator(int capacity, int numberOfUnique, Set<String> required) {
        Objects.requireNonNull(required);
        if (capacity < 1 || numberOfUnique < 1) {
            throw new IllegalArgumentException("capacity < 1 || numberOfUnique < 1");
        }
        if (capacity < numberOfUnique) {
            throw new IllegalArgumentException("capacity < numberOfUnique");
        }
        if (numberOfUnique < required.size()) {
            throw new IllegalArgumentException("numberOfUnique < required.size()");
        }

        this.capacity = capacity;
        this.uniqueIpStrings = new HashSet<>(numberOfUnique);
        this.uniqueIpStrings.addAll(required);

        while (uniqueIpStrings.size() < numberOfUnique) {
            String randomIpString;
            do {
                randomIpString = createRandomIpString();
            } while (uniqueIpStrings.contains(randomIpString));
            uniqueIpStrings.add(randomIpString);
        }

        uniqueIterator = uniqueIpStrings.iterator();
    }

    private final Random random = new Random();

    private String createRandomIpString() {
        return random.nextInt(256) + "."
                + random.nextInt(256) + "."
                + random.nextInt(256) + "."
                + random.nextInt(256);
    }

    private int counter;

    @Override
    public boolean hasNext() {
        if (!uniqueIterator.hasNext()) {
            uniqueIterator = uniqueIpStrings.iterator();
        }
        return counter < capacity;
    }

    @Override
    public String next() {
        counter++;
        return uniqueIterator.next();
    }
}
