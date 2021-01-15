package enviroment;

public class ThrottleBody implements Sensor {

    private int throttlePosition;

    @Override
    public int getValue() {
        return throttlePosition;
    }
}
