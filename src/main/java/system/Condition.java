package system;

import enviroment.Sensor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Condition {

    private final String name;
    private final String desc;
    private final int threshold;
    private final RelationalOperator relationalOperator;

    private Sensor sensor;

    public Condition(String name, String desc, RelationalOperator relationalOperatorToBeTrue, int threshold, Sensor sensor) {
        this.name = name;
        this.desc = desc;
        relationalOperator = relationalOperatorToBeTrue;
        this.threshold = threshold;
        this.sensor = sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public boolean evaluate(){
        if (sensor.getValue() < 0)
            return false;
        switch (relationalOperator) {
            case LESS_THAN:
                return sensor.getValue() < threshold;
            case LESS_OR_EQUAL_THAN:
                return sensor.getValue() <= threshold;
            case EQUAL_THAN:
                return sensor.getValue() == threshold;
            case MORE_OR_EQUAL_THAN:
                return sensor.getValue() >= threshold;
            case MORE_THAN:
                return sensor.getValue() > threshold;
            default:
                return false;
        }
    }

    public Sensor getSensor() {
        return sensor;
    }
}