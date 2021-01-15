package enviroment;

public class GyroscopeY implements Sensor{

    private int inclinationYAxis;
    @Override
    public int getValue() {
        return inclinationYAxis;
    }
}
