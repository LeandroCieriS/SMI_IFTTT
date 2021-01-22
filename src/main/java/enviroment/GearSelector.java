package enviroment;

public class GearSelector implements Sensor{
    private int gearEngaged;

    @Override
    public int getValue() {
        return gearEngaged;
    }
}
