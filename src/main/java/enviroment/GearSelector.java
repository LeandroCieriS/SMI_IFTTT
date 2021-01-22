package enviroment;

public class GearSelector implements Sensor{
    private int gearEngaged=-1;

    @Override
    public int getValue() {
        return gearEngaged;
    }
}
