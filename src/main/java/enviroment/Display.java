package enviroment;

public class Display implements Actuator {

    public void showInclinationAngle(int degrees){

    }

    public void sideFallAlert(Risk risk){
        switch (risk){
            case LOW:
                break;
            case MEDIUM:
                break;
            case HIGH:
                break;
            case EXTREME:
                break;
        }
    }
}
