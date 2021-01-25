package system;

import UI.MainDashboard;
import enviroment.*;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Sensor> sensors = new ArrayList<Sensor>(){{
            add(coolantTemp);
            add(gearSelector);
            add(gyroscopeX);
            add(gyroscopeY);
            add(speedometer);
            add(tachometer);
            add(throttleBody);
        }};

        //UI
        MainDashboard mainDashboard = new MainDashboard(sensors);
        mainDashboard.setSize(750,450);
        mainDashboard.setVisible(true);


        //Rules

        Rule ms01 = new Rule(1,"Launch Control", "Activates a soft limiter on revs while standing still");
        Condition c1 = new Condition("Standing","Speed is lower or equal than 2 kph", RelationalOperator.LESS_OR_EQUAL_THAN, 2, speedometer);
        Condition c2 = new Condition("First gear","The transmission has first gear engaged", RelationalOperator.EQUAL_THAN, 1, gearSelector);
        Action a1 = new Action("Soft limiter", "RPMs are limited to 3500", ecuManagerLimitRPM, 3500);
        ms01.setActions(a1);
        ms01.setConditions(c1,c2);

        Rule ms02 = new Rule(2, "Wheelie control", "Closes the throttle if a wheelie becomes decontrolled");
        Condition c3 = new Condition("Moving","Speed is higher than 2 kph", RelationalOperator.MORE_THAN, 2, speedometer);
        Condition c4 = new Condition("Wheelie","Bike is inclined in the X axis", RelationalOperator.MORE_THAN, 15, gyroscopeY);
        Action a2 = new Action("Close gas", "Closes the throttle", ecuManagerCloseThrottle, 0);
        ms02.setActions(a2);
        ms02.setConditions(c3,c4);

        Rule ms03 = new Rule(3, "lean info", "displays information about the lean angle");
        Condition c5 = new Condition("Leaning", "The motorcycle is leaning", RelationalOperator.MORE_THAN, 0, gyroscopeX);
        Condition c6 = new Condition("Moving", "The motorcycle is moving", RelationalOperator.MORE_THAN, 2, speedometer);
        Action a3 = new Action("Display lean angle", "Shows the angle the gyroscope X is reading", display, gyroscopeX.getValue());
        Action a4 = new Action("Display fall alert", "Shows the risk level of the current lean angle", fallAlert, 0);
        ms03.setActions(a3, a4);
        ms03.setConditions(c5, c6);

        Rule ms04 = new Rule(4, "Corner Exit", "Reduces the available throttle on corners exit");
        Condition c7 = new Condition("Leaning a lot", "The motorcycle is leaning more than 30 degrees", RelationalOperator.MORE_THAN, 30, gyroscopeX);
        Condition c8 = new Condition("Moving", "The motorcycle is moving", RelationalOperator.MORE_THAN, 10, speedometer);
        Action a5 = new Action("Reduce gas", "Reduces the available throttle to 60%", ecuManagerCloseThrottle, 60);
        ms04.setActions(a5);
        ms04.setConditions(c7, c8);

        Rule ms05 = new Rule(5, "Cold engine protection", "Reduces the available revs when the engine is cold");
        Condition c9 = new Condition("Neutral gear","The transmission has no gear engaged", RelationalOperator.EQUAL_THAN, 0, gearSelector);
        Condition c10 = new Condition("Engine is cold", "The coolant in the engine is below 70ºC", RelationalOperator.LESS_OR_EQUAL_THAN, 70, coolantTemp);
        Action a6 = new Action("Soft limiter", "RPMs are limited to 3500", ecuManagerLimitRPM, 3500);
        ms05.setActions(a6);
        ms05.setConditions(c9, c10);
        
        Profile beginnerProfile = new Profile(1,"Beginner");

        beginnerProfile.setRules(ms01, ms02, ms03);

        Profile intermediateProfile = new Profile(1,"Intermediate");

        intermediateProfile.setRules(ms03, ms04);

        Profile experiencedProfile = new Profile(1,"Experienced");

        experiencedProfile.setRules(ms03);

        List<Rule> rules = beginnerProfile.getRules();
        for (Rule rule: rules) {
            Thread rc = new RuleController(rule, mainDashboard);
            rc.start();
        }
    }
}
