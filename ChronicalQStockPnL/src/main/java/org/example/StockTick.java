package org.example;
import java.io.Serializable;

public class StockTick implements Serializable {
    private final String symbol;
    private final double price;
    private final long timestamp;

    public StockTick(String symbol, double price, long timestamp) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "StockTick{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}
