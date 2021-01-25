import enviroment.*;
import org.junit.Test;
import system.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class RuleTest {

    @Test
    public void should_not_met_without_conditions(){
        ECUManagerLimitRPM ecuManagerLimitRPM = new ECUManagerLimitRPM();

        Rule ms01 = new Rule(1,"Launch Control", "Activates a soft limiter on revs while standing still");
        Action a1 = new Action("Soft limiter", "RPMs are limited to 3500", ecuManagerLimitRPM, 3500);

        ms01.setActions(a1);
        ms01.triggerActions();

        assertFalse(ms01.allConditionsAreMet());
        assertNotEquals(3500, ecuManagerLimitRPM.getValue());
    }

    @Test
    public void should_met_only_condition(){
        Tachometer tachometer = mock(Tachometer.class);
        doReturn(1100).when(tachometer).getValue();

        ECUManagerLimitRPM ecuManagerLimitRPM = new ECUManagerLimitRPM();

        Rule ms01 = new Rule(1,"Launch Control", "Activates a soft limiter on revs while standing still");
        Condition c2 = new Condition("Engine is idling","RPMs are below 1750", RelationalOperator.LESS_THAN, 1750, tachometer);
        Action a1 = new Action("Soft limiter", "RPMs are limited to 3500", ecuManagerLimitRPM, 3500);

        ms01.setActions(a1);
        ms01.setConditions(c2);
        ms01.triggerActions();

        assertTrue(ms01.allConditionsAreMet());
        assertEquals(3500, ecuManagerLimitRPM.getValue());
    }

    @Test
    public void should_met_all_conditions(){
        Speedometer speedometer = mock(Speedometer.class);
        Tachometer tachometer = mock(Tachometer.class);

        doReturn(0).when(speedometer).getValue();
        doReturn(1100).when(tachometer).getValue();

        ECUManagerLimitRPM ecuManagerLimitRPM = new ECUManagerLimitRPM();

        Rule ms01 = new Rule(1,"Launch Control", "Activates a soft limiter on revs while standing still");
        Condition c1 = new Condition("Standing","Speed is lower or equal than 2 kph", RelationalOperator.LESS_OR_EQUAL_THAN, 2, speedometer);
        Condition c2 = new Condition("Engine is idling","RPMs are below 1750", RelationalOperator.LESS_THAN, 1750, tachometer);
        Action a1 = new Action("Soft limiter", "RPMs are limited to 3500", ecuManagerLimitRPM, 3500);

        ms01.setActions(a1);
        ms01.setConditions(c1,c2);
        ms01.triggerActions();

        assertTrue(ms01.allConditionsAreMet());
        assertEquals(3500, ecuManagerLimitRPM.getValue());
    }

    @Test
    public void should_not_met_all_conditions(){
        Speedometer speedometer = mock(Speedometer.class);
        GyroscopeX gyroscopeX = mock(GyroscopeX.class);

        doReturn(20).when(speedometer).getValue();
        doReturn(0).when(gyroscopeX).getValue();

        ECUManagerCloseThrottle ecuManagerCloseThrottle = new ECUManagerCloseThrottle();

        Rule ms02 = new Rule(2, "Wheelie control", "Closes the throttle if a wheelie becomes decontrolled");
        Condition c3 = new Condition("Moving","Speed is higher than 2 kph", RelationalOperator.MORE_THAN, 2, speedometer);
        Condition c4 = new Condition("Wheelie","Bike is inclined in the X axis", RelationalOperator.MORE_THAN, 15, gyroscopeX);
        Action a2 = new Action("Close gas", "Closes the throttle", ecuManagerCloseThrottle, 0);

        ms02.setActions(a2);
        ms02.setConditions(c3,c4);
        ms02.triggerActions();

        assertFalse(ms02.allConditionsAreMet());
        assertNotEquals(0, ecuManagerCloseThrottle.getValue());
    }

    @Test
    public void should_not_met_any_conditions(){
        CoolantTempSensor coolantTempSensor = mock(CoolantTempSensor.class);
        GearSelector gearSelector = mock(GearSelector.class);
        Tachometer tachometer = mock(Tachometer.class);

        doReturn(95).when(coolantTempSensor).getValue();
        doReturn(5).when(gearSelector).getValue();
        doReturn(6500).when(tachometer).getValue();

        ECUManagerLimitRPM ecuManagerLimitRPM = new ECUManagerLimitRPM();

        Rule ms05 = new Rule(5, "Protect cold engine", "Limit the throttle if the engine is cold and it's in neutral");
        Condition c5 = new Condition("Neutral","the transmission has neutral gear engaged", RelationalOperator.EQUAL_THAN, 0, gearSelector);
        Condition c6 = new Condition("Cold engine","The engine is not warned up enough", RelationalOperator.LESS_THAN, 70, coolantTempSensor);
        Condition c7 = new Condition("Engine is idling","RPMs are below 1750", RelationalOperator.LESS_THAN, 1750, tachometer);
        Action a1 = new Action("Soft limiter", "RPMs are limited to 4000", ecuManagerLimitRPM, 4000);

        ms05.setActions(a1);
        ms05.setConditions(c5, c6, c7);
        ms05.triggerActions();

        assertFalse(ms05.allConditionsAreMet());
        assertNotEquals(4000, ecuManagerLimitRPM.getValue());
    }
}