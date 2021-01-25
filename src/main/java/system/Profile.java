package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Profile {
    private final int ID;
    private final String profileName;

    private List<Rule> rules = new ArrayList();

    public Profile(int id, String profileName) {
        ID = id;
        this.profileName = profileName;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(Rule... rules) {
        Collections.addAll(this.rules, rules);
    }

    public String getProfileName() {
        return profileName;
    }
}
