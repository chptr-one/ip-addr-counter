package chptr.one.ip_counter;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.function.ToLongFunction;

public class IpStringHashFunction implements ToLongFunction<String> {

    @Override
    public long applyAsLong(@NotNull String ipString) {
        Objects.requireNonNull(ipString);
        long result = 0;
        try {
            for (byte b : InetAddress.getByName(ipString).getAddress())
                result = (result << 8) | (b & 255);
            return result;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
