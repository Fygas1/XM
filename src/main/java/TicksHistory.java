import java.time.LocalDateTime;
import java.util.List;

public interface TicksHistory {
    public void saveTicks(List<Tick> ticks);
    public LocalDateTime getLastTimestamp(String symbol);
    public void setLastTimestamp(String symbol, LocalDateTime timestamp);
}
