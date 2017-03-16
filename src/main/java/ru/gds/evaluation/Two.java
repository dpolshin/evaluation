package ru.gds.evaluation;

import java.util.*;

/**
 * A more flexible implementation of state util.
 * May use external source of states.
 * May render localized state names.
 */
public class Two {
    public static void main(String[] args) {
        Two two = new Two();
        two.loadStates();
        System.out.println(two.createStateSelectList());
    }


    private Map<String, State> states;
    //May be loaded from external configuration, database or depend on user's locale.
    //Hardcoded for demo only.
    private Locale locale = Locale.US;
    private String ls = System.lineSeparator();


    //May also use external configuration like property files of database.
    //Population method below is given for demo only.
    private void loadStates() {
        Map<String, State> map = new HashMap<>();

        State alabama = new State("AL", "Alabama");
        State alaska = new State("AK", "Alaska");
        State arizona = new State("AZ", "Arizona");
        State arkansas = new State("AR", "Arkansas");
        State california = new State("CA", "California");

        map.put(alabama.code, alabama);
        map.put(alaska.code, alaska);
        map.put(arizona.code, arizona);
        map.put(arkansas.code, arkansas);
        map.put(california.code, california);
        states = Collections.unmodifiableMap(map);
    }

    //May be cached for a given locale.
    //Value in the selection list changed from state name to code.
    public String createStateSelectList() {
        StringBuilder sb = new StringBuilder("<select name=\"state\">").append(ls);
        Formatter formatter = new Formatter(sb, locale);

        states.forEach((k, v) -> formatter.format("<option value=\"%s\">%s</option>%s", k, v.getLocalizedName(locale), ls));

        sb.append("</select>").append(ls);
        return sb.toString();
    }


    public State parseSelectedState(String s) {
        return states.get(s);
    }

    public String displayStateFullName(String abbr) {
        return states.get(abbr).getLocalizedName(locale);
    }


    /**
     * DTO for state object.
     * May also have other useful fields.
     * May or may not be inner class.
     */
    static class State {
        private String code;
        private String name;

        public State(String code, String name) {
            this.name = name;
            this.code = code;
        }

        public String getLocalizedName(Locale locale) {
            //Locale is ignored for demo usage.
            //May fetch from external resource like property files of database.
            return name;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(name, state.name) &&
                    Objects.equals(code, state.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, code);
        }
    }

}
