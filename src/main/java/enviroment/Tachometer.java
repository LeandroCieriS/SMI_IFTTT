package enviroment;

public class Tachometer implements Sensor{

    private int RPMs;

    @Override
    public int getValue() {
        RPMs = (int) (Math.random()*10000);
        return RPMs;
    }
}
