package enviroment;

public class ECUManagerCloseThrottle implements Actuator {

    private int percentageThrottleIsOpen = 100;

    @Override
    public void execute(int percentageThrottleIsOpen) {
        this.percentageThrottleIsOpen = percentageThrottleIsOpen;
    }

    @Override
    public void resetValue() {
        percentageThrottleIsOpen = 100;
    }

    @Override
    public int getValue() {
        return percentageThrottleIsOpen;
    }
}
