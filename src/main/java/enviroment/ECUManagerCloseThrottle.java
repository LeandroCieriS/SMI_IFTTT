package enviroment;

public class ECUManagerCloseThrottle implements Actuator {

    private int percentageThrottleIsOpen=-1;

    public int getPercentageThrottleIsOpen() {
        return percentageThrottleIsOpen;
    }

    @Override
    public void execute(int percentageThrottleIsOpen) {
        if (percentageThrottleIsOpen >= 0 && percentageThrottleIsOpen <=100)
            this.percentageThrottleIsOpen = percentageThrottleIsOpen;
    }
}
