package system;

import enviroment.Sensor;

public class Condition {

    private final String name;
    private final String desc;
    private final int threshold;
    private final RelationalOperator relationalOperator;

    private Sensor sensor;

    public Condition(String name, String desc, RelationalOperator relationalOperatorToBeTrue, int threshold) {
        this.name = name;
        this.desc = desc;
        relationalOperator = relationalOperatorToBeTrue;
        this.threshold = threshold;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public boolean evaluate(){
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
}