import enviroment.*;
import org.junit.Test;
import system.*;

import static org.junit.Assert.*;

public class ActionTest {

    @Test
    public void evaluate_action_with_Display(){
        Display display = new Display();
        Action a1 = new Action("display inclination", "Shows in a display the inclination  in the Y axis", display, 45);
        a1.execute();

        assertEquals(display.getDegrees(), 45);
    }

    @Test
    public void evaluate_action_with_ECUManagers(){
        ECUManagerCloseThrottle ecuManagerCloseThrottle = new ECUManagerCloseThrottle();
        ECUManagerLimitRPM ecuManagerLimitRPM = new ECUManagerLimitRPM();
        Action a1 = new Action("Close throttle", "opens the throttle body to a certain percentage", ecuManagerCloseThrottle, 0);
        Action a2 = new Action("Limit RPMs", "Limit the maximum RPMs the engine is able to reach", ecuManagerLimitRPM, 4500);
        a1.execute();
        a2.execute();

        assertEquals(ecuManagerCloseThrottle.getPercentageThrottleIsOpen(), 0);
        assertEquals(ecuManagerLimitRPM.getMaxRPMs(), 4500);
    }

    @Test
    public void evaluate_action_with_FallAlert(){
        FallAlert fallAlert = new FallAlert();
        FallAlert fallAlert2 = new FallAlert();
        Action a1 = new Action("Fall risk low", "Shows there is no fall risk or is very low", fallAlert, 0);
        Action a2 = new Action("Fall risk extreme", "Shows there is extreme fall risk", fallAlert2, 3);
        a1.execute();
        a2.execute();

        assertEquals(fallAlert.getRisk(), Risk.LOW);
        assertEquals(fallAlert2.getRisk(), Risk.EXTREME);
    }
}
