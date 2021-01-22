package system;

import enviroment.*;

public class App {

    public static void main(String[] args) {

        //Actuators
        ECUManagerLimitRPM ecuManagerLimitRPM = new ECUManagerLimitRPM();
        ECUManagerCloseThrottle ecuManagerCloseThrottle = new ECUManagerCloseThrottle();
        Display display = new Display();
        FallAlert fallAlert = new FallAlert();

        //Sensors
        CoolantTempSensor coolantTemp = new CoolantTempSensor();
        GearSelector gearSelector = new GearSelector();
        GyroscopeX gyroscopeX = new GyroscopeX();
        GyroscopeY gyroscopeY = new GyroscopeY();
        Speedometer speedometer = new Speedometer();
        Tachometer tachometer = new Tachometer();
        ThrottleBody throttleBody = new ThrottleBody();

        Account account = new Account(1,"Beginner", "123456", "beginner@test.com");

        Rule ms01 = new Rule(1,"Launch Control", "Activates a soft limiter on revs while standing still");
        Condition c1 = new Condition("Standing","Speed is lower or equal than 2 kph", RelationalOperator.LESS_OR_EQUAL_THAN, 2, speedometer);
        Condition c2 = new Condition("Engine is idling","RPMs are below 1750", RelationalOperator.LESS_THAN, 1750, tachometer);
        Action a1 = new Action("Soft limiter", "RPMs are limited to 3500", ecuManagerLimitRPM, 3500);

        ms01.setActions(a1);
        ms01.setConditions(c1,c2);

        Rule ms02 = new Rule(2, "Wheelie control", "Closes the throttle if a wheelie becomes decontrolled");
        Condition c3 = new Condition("Moving","Speed is higher than 2 kph", RelationalOperator.MORE_THAN, 2, speedometer);
        Condition c4 = new Condition("Wheelie","Bike is inclined in the X axis", RelationalOperator.MORE_THAN, 15, gyroscopeX);
        Action a2 = new Action("Close gas", "Closes the throttle", ecuManagerCloseThrottle, 0);

        ms02.setActions(a2);
        ms02.setConditions(c3,c4);

        account.setRules(ms01, ms02);
    }
}
