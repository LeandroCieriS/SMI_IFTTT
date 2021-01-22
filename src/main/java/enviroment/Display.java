package enviroment;

public class Display implements Actuator {

    private int degrees;

    @Override
    public void execute(int degrees) {
        this.degrees = degrees;
    }
}
