package enviroment;

public class CoolantTempSensor implements Sensor {
    private int coolantTemp=-1;

    @Override
    public int getValue() {
        return coolantTemp;
    }
}
