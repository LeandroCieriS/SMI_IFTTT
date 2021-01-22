package enviroment;

public class ThrottleBody implements Sensor {

    private int throttlePosition=-1;

    @Override
    public int getValue() {
        return throttlePosition;
    }
}
