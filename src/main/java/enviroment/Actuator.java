package enviroment;

public interface Actuator {
    void execute(int value);
    void resetValue();
    int getValue();
}
