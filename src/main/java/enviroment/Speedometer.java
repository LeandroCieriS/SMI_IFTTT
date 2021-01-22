package enviroment;

public class Speedometer implements Sensor {

    private int speed=-1;

    @Override
    public int getValue() {
        return speed;
    }
}
