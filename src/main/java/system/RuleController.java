package system;

import UI.MainDashboard;

import static java.lang.Integer.min;

public class RuleController extends Thread{

    private final Rule rule;
    private final MainDashboard mainDashboard;

    public RuleController(Rule rule, MainDashboard mainDashboard){
        this.rule = rule;
        this.mainDashboard = mainDashboard;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (rule.getID() == 3) {
                    trampaParaRegla3();
                } else {
                    rule.triggerActions();

                    for (Action a : rule.getActions()) {
                        mainDashboard.setValueFromActuator(a.getActuator().getClass().getSimpleName(), a.getActuator().getValue());
                    }
                }
            }
        }
    }

    private void trampaParaRegla3() {
        Action display = rule.getActions().get(0);
        Condition leaning = rule.getConditions().get(0);

        rule.triggerActions();

        int leanAngle = leaning.getSensor().getValue();
        display.setValue(leanAngle);

        mainDashboard.setValueFromActuator(display.getActuator().getClass().getSimpleName(), display.getActuator().getValue());

        Action displayRisk = rule.getActions().get(1);
        //Condition moving = rule.getConditions().get(1);

        int risk = min(leanAngle/15, 3) ;
        displayRisk.setValue(risk);
        mainDashboard.setValueFromActuator(displayRisk.getActuator().getClass().getSimpleName(), displayRisk.getActuator().getValue());

    }
}
