package enviroment;

public class CoolantTempSensor implements Sensor {
    private int coolantTemp;

    @Override
    public int getValue() {
        return coolantTemp;
    }
}
