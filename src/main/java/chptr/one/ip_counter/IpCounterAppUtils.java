package chptr.one.ip_counter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class IpCounterAppUtils {

    public static Optional<String> parseAndCheckFileName(String[] args) {
        if (args.length != 2 || !"-f".equals(args[0])) {
            System.err.println("Wrong arguments. Use param -f file_name.");
            return Optional.empty();
        }
        String fileName = args[1];
        if (!Files.exists(Paths.get(fileName))) {
            System.err.println("File " + fileName + " does not exists.");
            return Optional.empty();
        }
        return Optional.of(fileName);
    }

    public static void printResults(long unique, long linesProcessed, long fileSize, long nanoseconds) {
        double timeInSeconds = nanoseconds / 1_000_000_000.0;
        System.out.printf("Unique IP addresses : %,d\n", unique);
        System.out.printf("Lines processed     : %,d\n", linesProcessed);
        System.out.printf("Total time          : %.3f sec\n", timeInSeconds);
        System.out.printf("Processing speed    : %.3f KLines/sec\n", linesProcessed / 1000 / timeInSeconds);
        System.out.printf("Average disk speed  : %.3f MB/sec\n", fileSize / 1024 / 1024 / timeInSeconds);
    }
}
