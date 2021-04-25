package chptr.one.ip_counter;

import java.io.BufferedReader;
import java.io.IOException;

public class IpStringCounter {

    private final BufferedReader reader;

    private long uniqueIp;
    private long linesProcessed;

    public IpStringCounter(BufferedReader reader) {
        this.reader = reader;
    }

    public void processFile()  {
        uniqueIp = 0;
        linesProcessed = 0;
        ArrayBitVector bitVector = new ArrayBitVector(1L << 32);
        IpStringHashFunction hashFunction = new IpStringHashFunction();
        String ipString;

        while (true) {
            try {
                if ((ipString = reader.readLine()) == null) break;
                linesProcessed++;
                bitVector.setBit(hashFunction.applyAsLong(ipString));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        uniqueIp = bitVector.getCardinality();
    }

    public long getUniqueIp() {
        return uniqueIp;
    }

    public long getLinesProcessed() {
        return linesProcessed;
    }
}
