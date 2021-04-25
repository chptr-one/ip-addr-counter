package chptr.one.ip_counter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class IpCounterAppUtils {

    public static Optional<String> checkArgsAndGetFileName(String[] args) {
        if (args.length != 2 || !"-file".equals(args[0])) {
            System.err.println("Wrong arguments. Use param -file file_name.");
            return Optional.empty();
        }
        String fileName = args[1];
        if (!Files.exists(Paths.get(fileName))) {
            System.err.println("File " + fileName + " does not exists.");
            return Optional.empty();
        }
        return Optional.of(fileName);
    }

    public static void printResults(IpStringCounter counter, long fileSize, long totalTime) {
        double timeInSeconds = totalTime / 1_000_000_000.0;
        System.out.printf("Unique IP addresses : %,d\n", counter.getUniqueIp());
        System.out.printf("Lines processed     : %,d\n", counter.getLinesProcessed());
        System.out.printf("Total time          : %.3f sec\n", timeInSeconds);
        System.out.printf("Processing speed    : %.3f KLines/sec\n", counter.getLinesProcessed() / 1000 / timeInSeconds);
        System.out.printf("Average disk speed  : %.3f MB/sec\n", fileSize / 1024 / 1024 / timeInSeconds);
    }
}
