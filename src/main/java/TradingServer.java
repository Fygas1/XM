import java.time.LocalDateTime;
import java.util.List;

public interface TradingServer {
    List<String> getSymbols();
    List<Tick> getTicksPeriod(String symbol, LocalDateTime from, LocalDateTime to);
}