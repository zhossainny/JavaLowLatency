package org.example;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptTailer;

public class PNLUpdaterConsumer {

    public static void main(String[] args) {
        try (ChronicleQueue queue = ChronicleQueue.single("stock-tick-queue")) {
            ExcerptTailer tailer = queue.createTailer();
            long lastUpdateTime = System.currentTimeMillis();

            while (true) {
                tailer.toEnd();
                boolean success = tailer.readDocument(w -> {
                    StockTick tick = w.read("stockTick").object(StockTick.class);
                    System.out.println("Consumed: " + tick);
                    // Process tick data as needed (e.g., accumulate PNL)
                });

                // Update PNL every second
                if (System.currentTimeMillis() - lastUpdateTime >= 1000) {
                    System.out.println("Updating PNL based on recent data...");
                    lastUpdateTime = System.currentTimeMillis();
                }
            }
        }
    }
}

