import enviroment.Sensor;
import enviroment.Tachometer;
import logger.LoggerFactory;
import system.Action;
import system.Condition;
import system.RelationalOperator;
import system.Rule;

public class App {
    public static void main(String[] args) {

        Sensor tachometer = new Tachometer();
        // Use this logged tachometer instead of the normal one
        Sensor loggerTachometer = LoggerFactory.getLoggerTachometer(tachometer);

        Rule rule0 = new Rule(0, "TestSpeed", "just a test");
        Condition conditionMoreThan = new Condition("TestCondition","just a test", RelationalOperator.MORE_THAN, 0, loggerTachometer);
        Action action = new Action("TestAction", "just a test");

        rule0.addCondition(conditionMoreThan);
        rule0.addAction(action);

        rule0.execute();

    }
}
