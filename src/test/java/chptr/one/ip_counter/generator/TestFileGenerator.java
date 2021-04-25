package chptr.one.ip_counter.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@SuppressWarnings("unused")
public class TestFileGenerator {
    private final Path filePath;
    private final int capacity;
    private final int unique;
    private final Set<String> required;

    public TestFileGenerator(Path filePath, int capacity, int unique, Set<String> required) {
        this.filePath = filePath;
        this.capacity = capacity;
        this.unique = unique;
        this.required = Set.copyOf(required);
    }

    public void createTestFile() throws IOException {
        try (BufferedWriter out = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            var iterator = new RandomIpStringIterator(capacity, unique, required);
            while (iterator.hasNext()) {
                out.write(iterator.next() + '\n');
            }
        }
    }
}
