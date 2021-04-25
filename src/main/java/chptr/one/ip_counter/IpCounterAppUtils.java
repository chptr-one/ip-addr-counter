package chptr.one.ip_counter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IpCounterAppUtils {

    public static Path checkArgsAngParsePath(String[] args) {
        if (!(args.length == 2) || !"-file".equals(args[0])) {
            System.err.println("Wrong arguments. Use param -file file_name.");
            return null;
        }

        Path filePath = Paths.get(args[1]);
        if (!Files.exists(filePath)) {
            System.err.println("File " + filePath + "does not exists.");
            return null;
        }

        return filePath;
    }

    public static void printResults(IpStringCounter counter, long fileSize, long totalTime) {
        double timeInSeconds = totalTime / 1_000_000_000.0;
        System.out.printf("Unique IP addresses : %,d\n", counter.getUniqueIp());
        System.out.printf("Lines processed     : %,d lines\n", counter.getLinesProcessed());
        System.out.printf("Total time          : %.3f sec\n", timeInSeconds);
        System.out.printf("Processing speed    : %.2f KLines/sec\n", counter.getLinesProcessed() / 1000 / timeInSeconds);
        System.out.printf("Average disk speed  : %.2f MB/sec\n", fileSize / 1024 / 1024 / timeInSeconds);
    }
}
