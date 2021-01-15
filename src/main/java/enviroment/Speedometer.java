package enviroment;

public class Speedometer implements Sensor {

    private int speed;

    @Override
    public int getValue() {
        return speed;
    }
}
