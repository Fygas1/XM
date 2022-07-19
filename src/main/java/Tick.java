import java.time.LocalDateTime;

public class Tick {

    private final LocalDateTime timestamp;
    private final String symbol;
    private final double ask;
    private final double bid;

    public Tick(LocalDateTime timestamp,String symbol, double bid, double ask) {
        this.timestamp=timestamp;
        this.symbol = symbol;
        this.bid = bid;
        this.ask = ask;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public double getAsk(){
        return this.ask;
    }

    public double getBid(){
        return this.bid;
    }

    public String toString(){
        return timestamp.toString();
    }
}
