package enviroment;

public class ECUManagerLimitRPM implements Actuator {

    private int percentageThrottleIsOpen;
    private int maxRPMs;

    public void closeThrottlePartially(int percentageThrottleIsOpen){
        if (percentageThrottleIsOpen >= 0 && percentageThrottleIsOpen <=100)
            this.percentageThrottleIsOpen = percentageThrottleIsOpen;
    }

    public void limitRevs(int rpm){
        maxRPMs = rpm;
    }

    public int getPercentageThrottleIsOpen() {
        return percentageThrottleIsOpen;
    }

    public int getMaxRPMs() {
        return maxRPMs;
    }

    @Override
    public void execute(int limitRPMs) {

    }
}
