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

    Tachometer tachometer = mock(Tachometer.class);
    Tachometer tachometer2 = mock(Tachometer.class);
    Speedometer speedometer = mock(Speedometer.class);
    ThrottleBody throttleBody = mock(ThrottleBody.class);
    GyroscopeX gyroscopex = mock(GyroscopeX.class);

    @Test
    public void evaluate_condition_LESS_THAN_sensor_value(){
        doReturn(1100).when(tachometer).getValue();
        doReturn(2100).when(tachometer2).getValue();

        Condition condition = new Condition("Engine is idling","RPMs are below and 1750", RelationalOperator.LESS_THAN, 1750, tachometer);
        assertTrue(condition.evaluate());

        Condition condition2 = new Condition("Engine is idling","RPMs are below and 1750", RelationalOperator.LESS_THAN, 1750, tachometer2);
        assertFalse(condition2.evaluate());
    }

    @Test
    public void evaluate_condition_LESS_OR_EQUAL_THAN_sensor_value(){
        doReturn(1750).when(tachometer).getValue();

        Condition condition = new Condition("Engine is idling","RPMs are below and 1750", RelationalOperator.LESS_OR_EQUAL_THAN, 1750, tachometer);

        assertTrue(condition.evaluate());
    }

    @Test
    public void evaluate_condition_EQUAL_THAN_sensor_value(){
        doReturn(0).when(speedometer).getValue();

        Condition condition = new Condition("Standing still","Motorcycle's speed is 0", RelationalOperator.EQUAL_THAN, 0, speedometer);

        assertTrue(condition.evaluate());
    }

    @Test
    public void evaluate_condition_MORE_OR_EQUAL_THAN_sensor_value(){
        doReturn(90).when(throttleBody).getValue();

        Condition condition = new Condition("Full gas","Throttle body is open at 90% or more", RelationalOperator.MORE_OR_EQUAL_THAN, 90, throttleBody);

        assertTrue(condition.evaluate());
    }

    @Test
    public void evaluate_condition_MORE_THAN_sensor_value(){
        doReturn(35).when(gyroscopex).getValue();

        Condition condition = new Condition("Wheelie","Motorcycle is inclined positively on X axis", RelationalOperator.MORE_THAN, 25, gyroscopex);

        assertTrue(condition.evaluate());
    }
}
