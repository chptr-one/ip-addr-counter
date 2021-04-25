package chptr.one.ip_counter;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class IpCounterApp {

    public static void main(String[] args) {
        Path path = IpCounterAppUtils.checkArgsAngParsePath(args);
        if (path == null) {
            return;
        }

        ProgressBarBuilder pbb = new ProgressBarBuilder()
                .setTaskName("Reading " + path.getFileName())
                .setUnit("MB", 1048576); // setting the progress bar to use MB as the unit

        long startTime = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                ProgressBar.wrap(new FileInputStream(String.valueOf(path)), pbb)))) {

            IpStringCounter counter = new IpStringCounter(reader);
            counter.processFile();
            double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0; // in seconds
            System.out.printf("Unique IP addresses : %,d\n", counter.getUniqueIp());
            System.out.printf("Lines processed     : %,d lines\n", counter.getLinesProcessed());
            System.out.printf("Total time          : %.3f sec\n", totalTime);
            System.out.printf("Processing speed    : %.2f KLines/sec\n", counter.getLinesProcessed() / 1000 / totalTime);
            System.out.printf("Average disk speed  : %.2f MB/sec\n", Files.size(path) / 1024 / 1024 / totalTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
