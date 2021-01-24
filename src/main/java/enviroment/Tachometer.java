package enviroment;

public class Tachometer implements Sensor{

    private int RPMs=-1;

    @Override
    public int getValue() {
        return RPMs;
    }

    @Override
    public void dataSource(int valueFromSource) {
        RPMs = valueFromSource;
    }
}
