import enviroment.GyroscopeX;
import enviroment.Speedometer;
import enviroment.Tachometer;
import enviroment.ThrottleBody;
import org.junit.Test;
import system.Condition;
import system.RelationalOperator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ConditionTest {

    @Test
    public void evaluate_condition_LESS_THAN_sensor_value(){
        //initialize mock sensor
        Tachometer tachometer = mock(Tachometer.class);
        doReturn(1100).when(tachometer).getValue();
        Tachometer tachometer2 = mock(Tachometer.class);
        doReturn(2100).when(tachometer2).getValue();

        //Initialize condition and join mock sensor
        Condition condition = new Condition("Engine is idling","RPMs are below and 1750", RelationalOperator.LESS_THAN, 1750);
        condition.setSensor(tachometer);
        assertTrue(condition.evaluate());

        condition.setSensor(tachometer2);
        assertFalse(condition.evaluate());
    }

    @Test
    public void evaluate_condition_LESS_OR_EQUAL_THAN_sensor_value(){
        //initialize mock sensor
        Tachometer tachometer = mock(Tachometer.class);
        doReturn(1750).when(tachometer).getValue();

        //Initialize condition and join mock sensor
        Condition condition = new Condition("Engine is idling","RPMs are below and 1750", RelationalOperator.LESS_OR_EQUAL_THAN, 1750);
        condition.setSensor(tachometer);

        assertTrue(condition.evaluate());
    }

    @Test
    public void evaluate_condition_EQUAL_THAN_sensor_value(){
        //initialize mock sensor
        Speedometer speedometer = mock(Speedometer.class);
        doReturn(0).when(speedometer).getValue();

        //Initialize condition and join mock sensor
        Condition condition = new Condition("Standing still","Motorcycle's speed is 0", RelationalOperator.EQUAL_THAN, 0);
        condition.setSensor(speedometer);

        assertTrue(condition.evaluate());
    }

    @Test
    public void evaluate_condition_MORE_OR_EQUAL_THAN_sensor_value(){
        //initialize mock sensor
        ThrottleBody throttleBody = mock(ThrottleBody.class);
        doReturn(90).when(throttleBody).getValue();

        //Initialize condition and join mock sensor
        Condition condition = new Condition("Full gas","Throttle body is open at 90% or more", RelationalOperator.MORE_OR_EQUAL_THAN, 90);
        condition.setSensor(throttleBody);

        assertTrue(condition.evaluate());
    }

    @Test
    public void evaluate_condition_MORE_THAN_sensor_value(){
        //initialize mock sensor
        GyroscopeX gyroscopex = mock(GyroscopeX.class);
        doReturn(35).when(gyroscopex).getValue();

        //Initialize condition and join mock sensor
        Condition condition = new Condition("Wheelie","Motorcycle is inclined positively on X axis", RelationalOperator.MORE_THAN, 25);
        condition.setSensor(gyroscopex);

        assertTrue(condition.evaluate());
    }
}
