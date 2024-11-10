package org.example;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;

import java.time.Instant;
import java.util.Random;

public class StockTickProducer {
    private static final String[] SYMBOLS = {"AAPL", "GOOG", "MSFT", "TSLA"};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try (ChronicleQueue queue = ChronicleQueue.single("stock-tick-queue")) {
            ExcerptAppender appender = queue.acquireAppender();

            while (true) {
                StockTick tick = generateRandomTick();
                appender.writeDocument(w -> w.write("stockTick").object(tick));
                System.out.println("Produced: " + tick);

                // Simulate rapid tick data (adjust as necessary)
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static StockTick generateRandomTick() {
        String symbol = SYMBOLS[RANDOM.nextInt(SYMBOLS.length)];
        double price = 100 + RANDOM.nextDouble() * 50;  // Random price between 100 and 150
        long timestamp = Instant.now().toEpochMilli();
        return new StockTick(symbol, price, timestamp);
    }
}

