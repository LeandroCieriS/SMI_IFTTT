package enviroment;

public class GyroscopeX implements Sensor {

    private int inclinationXAxis = -1;

    @Override
    public int getValue() {
        return inclinationXAxis;
    }

    @Override
    public void dataSource(int valueFromSource) {
        inclinationXAxis = valueFromSource;
    }
}