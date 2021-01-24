package enviroment;

public class ECUManagerLimitRPM implements Actuator {

    private int maxRPMs = 10000;

    @Override
    public void execute(int limitRPMs) {
        maxRPMs = limitRPMs;
    }

    @Override
    public void resetValue() {
        maxRPMs = 10000;
    }

    @Override
    public int getValue() {
        return maxRPMs;
    }
}
