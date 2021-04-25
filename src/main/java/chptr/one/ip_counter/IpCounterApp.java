package chptr.one.ip_counter;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarStyle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class IpCounterApp {

    private static void doTheWork(String fileName) {
        ProgressBarBuilder pbb = new ProgressBarBuilder()
                .setTaskName("Processing " + Path.of(fileName).getFileName())
                .setUnit("MB", 1048576); // setting the progress bar to use MB as the unit

        long startTime = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                ProgressBar.wrap(new FileInputStream(fileName), pbb)))) {
            IpStringCounter counter = new IpStringCounter(reader);
            counter.processFile();
            long totalTime = (System.nanoTime() - startTime);
            IpCounterAppUtils.printResults(counter, Files.size(Path.of(fileName)), totalTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Optional<String> optionalFileName = IpCounterAppUtils.checkArgsAndGetFileName(args);
        optionalFileName.ifPresent(IpCounterApp::doTheWork);
    }
}
