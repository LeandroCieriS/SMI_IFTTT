package enviroment;

public class ECUManagerLimitRPM implements Actuator {

    private int maxRPMs;

    public int getMaxRPMs() {
        return maxRPMs;
    }

    @Override
    public void execute(int limitRPMs) {
        maxRPMs = limitRPMs;
    }
}
