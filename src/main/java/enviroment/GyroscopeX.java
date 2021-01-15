public class GyroscopeX implements Sensor {

    private int inclinationXAxis;

    @Override
    public int getValue() {
        return inclinationXAxis;
    }
}