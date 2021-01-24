package enviroment;

public class Display implements Actuator {

    private int degrees;

    @Override
    public void execute(int degrees) {
        this.degrees = degrees;
    }

    @Override
    public void resetValue() {
        degrees = 0;
    }

    @Override
    public int getValue() {
        return degrees;
    }
}
