package enviroment;

public class GyroscopeY implements Sensor{

    private int inclinationYAxis=-1;
    @Override
    public int getValue() {
        return inclinationYAxis;
    }
}
