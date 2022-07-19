import java.time.LocalDateTime;
import java.util.*;

public class LPs {

    private HashMap<String,List<Tick>> TicksFromLP=new HashMap<>();
    private int symbolCounter;
    public LPs(){
        int seconds = 1;
        List<Tick> ticks = new ArrayList<Tick>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            LocalDateTime time = LocalDateTime.of(2022, 7, 8, 11, 24, seconds);
            int value = random.nextInt(3) + 1;
            if (value == 1) {
                double[] bidask=generateRandomBidAsk(1.01100,1.02500,1.02500,1.03000, 100000);
                Tick tick=new Tick(time,"EURUSD",bidask[0],bidask[1]);
                ticks.add(tick);
            }
            else if (value==2){
                double[] bidask=generateRandomBidAsk(138.300,138.350,138.355,138.400, 1000);
                Tick tick=new Tick(time,"EURJPY",bidask[0],bidask[1]);
                ticks.add(tick);
            }
            else {
                double[] bidask=generateRandomBidAsk(0.88300,0.88380,0.88390,0.8842, 100000);
                Tick tick=new Tick(time,"AUDCAD",bidask[0],bidask[1]);
                ticks.add(tick);
            }
            seconds++;
        }
        List<Tick> EURUSD=new ArrayList<>();
        List<Tick> EURJPY=new ArrayList<>();
        List<Tick> AUDCAD=new ArrayList<>();
        for (int i=0;i<ticks.size();i++){
            Tick tick=ticks.get(i);
            if (tick.getSymbol().equals("EURUSD")) EURUSD.add(tick);
            else if (tick.getSymbol().equals("EURJPY")) EURJPY.add(tick);
            else if (tick.getSymbol().equals("AUDCAD")) AUDCAD.add(tick);
        }
        this.TicksFromLP.put("EURUSD",EURUSD);
        this.TicksFromLP.put("EURJPY", EURJPY);
        this.TicksFromLP.put("AUDCAD", AUDCAD);
    }

    public HashMap<String,List<Tick>> getLPTicks(){
        return this.TicksFromLP;
    }

    private double generateRandom(double min, double max, int decimal){
        double value=0;
        Random random=new Random();
        value=(max - min) * random.nextDouble()+min;
        value=Math.floor(value*decimal)/decimal;
        return value;
    }

    private double[] generateRandomBidAsk(double bid_min, double bid_max, double ask_min, double ask_max, int decimal){
        double[] values=new double[2];
        values[0]=generateRandom(bid_min,bid_max,100000);
        values[1]=generateRandom(ask_min,ask_max,100000);
        return values;
    }

    public void printLPTicks(){
        System.out.println("---------LP TICKS---------");
        for (Map.Entry<String, List<Tick>> entry : getLPTicks().entrySet()) {
            String symbol = entry.getKey();
            Object ticks = entry.getValue();
            System.out.println(symbol+" Ticks: "+ticks);
        }
        System.out.println("------------------------");
    }
}
