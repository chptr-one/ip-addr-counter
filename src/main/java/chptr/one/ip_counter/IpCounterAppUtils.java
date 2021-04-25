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
}
