package chptr.one.ip_counter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IpCounterAppUtils {

    public static Path checkArgsAngParsePath(String[] args) {
        if (args.length == 2 && "-file".equals(args[0])) {
            Path filePath = Paths.get(args[1]);
            if (Files.exists(filePath)) {
                return filePath;
            } else {
                System.err.println("File " + filePath + "does not exists.");
            }
        } else {
            System.err.println("Wrong arguments. Use param -file file_name.");
        }
        return null;
    }
}
