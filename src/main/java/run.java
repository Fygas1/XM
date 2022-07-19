import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class run implements TicksHistory, TradingServer {

    private HashMap<String,LocalDateTime> LastTimestamps=new HashMap<>();
    private List<Tick> SavedTicks=new ArrayList<>();


    public static void main(String[] args){
        run run=new run();
        run.insert_ltm();
        List<String> symbolList=run.getSymbols();
        run.saveTicks(run.getSavedTicks());
        for (String symbol: symbolList){
            LocalDateTime time=run.getLastTimestamp(symbol);
            System.out.println(symbol+" last timestamp: "+time);
            List<Tick> symbolTicks=run.getTicks(symbol);
            System.out.println(symbol+" ticks: "+symbolTicks);
            run.removeDuplicates(symbolTicks);
            List<Tick> TicksPeriod=run.getTicksPeriod(symbol, time, time.plusHours(1));
            System.out.println(symbol+" tick from: "+time+" to: "+time.plusHours(1)+": "+TicksPeriod);
            run.setLastTimestamp(symbol,time.plusHours(1));
            time=run.getLastTimestamp(symbol);
            System.out.println(symbol+" updated last timestamp: "+time);
            System.out.println("--------------------------------");
        }
    }

    public void insert_ltm(){
        int counter=1;
        for (int i=0;i<3;i++){
            String symbol=null;
            if (i==0) symbol="EURUSD";
            else if (i==1) symbol="EURJPY";
            else symbol="AUDCAD";
            LastTimestamps.put(symbol,LocalDateTime.of(2022,7,8,11,24,counter));
            counter++;
            // System.out.println(counter);
        }
    }

    public void printSymbols(){
        System.out.println("Symbol list: ");
        System.out.println("------------");
        for (String symbol: LastTimestamps.keySet()) {
            String value=LastTimestamps.get(symbol).toString();
            System.out.println(" | "+symbol+" | ");
            System.out.println("------------");
        }
    }

    public List<Tick> getSavedTicks(){
        return this.SavedTicks;
    }
    public List<Tick> getTicks(String symbol){
        List<Tick> tick_list=new ArrayList<>();
        for (Tick tick : getSavedTicks()){
            if (tick.getSymbol().equals(symbol)) tick_list.add(tick);
        }
        if (tick_list.isEmpty()) {
            System.out.println("Symbol not found!");
            return null;
        }
        else return tick_list;
    }
    @Override
    public void saveTicks(List<Tick> ticks) {
        LPs lp=new LPs();
        for (List<Tick> value : lp.getLPTicks().values()) {
            this.SavedTicks= Stream.concat(this.SavedTicks.stream(), value.stream()).collect(Collectors.toList());
        }
    }

    @Override
    public LocalDateTime getLastTimestamp(String symbol) {
        return this.LastTimestamps.get(symbol);
    }

    @Override
    public void setLastTimestamp(String symbol, LocalDateTime timestamp) {
        this.LastTimestamps.replace(symbol,timestamp);
    }

    @Override
    public ArrayList<String> getSymbols() {
        Set<String> keySet=LastTimestamps.keySet();
        ArrayList<String> list=new ArrayList<>(keySet);
        printSymbols();
        return list;
    }

    @Override
    public List<Tick> getTicksPeriod(String symbol, LocalDateTime from, LocalDateTime to) {
        List<Tick> results=new ArrayList<>();
        for (Tick tick : getSavedTicks()){
            LocalDateTime time=tick.getTimestamp();
            if (tick.getSymbol().equals(symbol)){
                if (((time.isEqual(from)) || time.isAfter(from)) && (time.isEqual(to) || time.isBefore(to))) results.add(tick);
            }
        }
        if (results.isEmpty()) {
            System.out.println("Not found");
            return null;
        }
        else return results;
    }

    public void removeDuplicates(List<Tick> ticks){
        ticks.stream().distinct().collect(Collectors.toList());
    }
}