package system;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private final int ID;
    private final String name;
    private final String desc;

    private List<Condition> conditions = new ArrayList();
    private List<Action> actions = new ArrayList();

    public Rule(int id, String name, String desc) {
        ID = id;
        this.name = name;
        this.desc = desc;
    }

    protected boolean allConditionsAreMet(){
        for (Condition con : conditions) {
            if (!con.evaluate()) return false;
        }
        return true;
    }

    protected void triggerActions(){
        for (Action act : actions) {
            act.execute();
        }
    }

    public void addCondition(Condition condition){
        conditions.add(condition);
    }

    public void addAction(Action action){
        actions.add(action);
    }

    public void execute(){
        if (allConditionsAreMet())
            triggerActions();
    }
}
