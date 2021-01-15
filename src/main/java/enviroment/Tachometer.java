package enviroment;

public class Tachometer implements Sensor{

    private int RPMs;

    @Override
    public int getValue() {
        return RPMs;
    }
}
