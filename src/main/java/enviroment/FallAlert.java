package enviroment;

import static enviroment.Risk.*;

public class FallAlert implements Actuator{
    private Risk risk;
    private int riskInt;

    @Override
    public void execute(int risk) {
        riskInt = risk;
        switch (risk){
            case 0:
                this.risk = LOW;
                break;
            case 1:
                this.risk = MEDIUM;
                break;
            case 2:
                this.risk = HIGH;
                break;
            case 3:
                this.risk = EXTREME;
                break;
        }
    }

    @Override
    public void resetValue() {
        risk = LOW;
    }

    @Override
    public int getValue() {
        return riskInt;
    }
}
