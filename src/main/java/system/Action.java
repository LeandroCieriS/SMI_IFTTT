package system;

import enviroment.Actuator;

public class Action {

    private final String name;
    private final String desc;
    private int value;

    private Actuator actuator;

    public Action(String name, String desc, Actuator actuator, int value) {
        this.name = name;
        this.desc = desc;
        this.value = value;
        this.actuator = actuator;
    }

    public void execute() {
        actuator.execute(value);
    }

    public Actuator getActuator() {
        return actuator;
    }

    public void setValue(int value){
        this.value = value;
    }
}
