package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final int ID;
    private final String userName;
    private final String password;
    private final String email;

    private List<Rule> rules = new ArrayList();

    public Account(int id, String userName, String password, String email) {
        ID = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(Rule... rules) {
        Collections.addAll(this.rules, rules);
    }
}
