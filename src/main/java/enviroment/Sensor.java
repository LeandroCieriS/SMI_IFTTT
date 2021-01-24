package enviroment;

public interface Sensor {
    int getValue();
    void dataSource(int valueFromSource); // Test Method only for connecting with the mock UI
}
