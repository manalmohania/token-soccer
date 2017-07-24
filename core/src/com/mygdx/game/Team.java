package com.mygdx.game;

public enum Team {
    Spain,
    Germany,
    Italy,
    England;

    public static String getResource(Team team){
        /*
        * ASSUMPTION: the file is named: teamname-32.png and is placed in images/tokens/
        * */
        if (team == null) {
            throw new NullPointerException("Team cannot be null");
        }
        return "images/tokens/" + ("" + team).toLowerCase() + "-32.png";
    }

    public static Team getTeamFromChar(char c) {
        /*
        * WARNING: Working under the assumption that no two teams have names staring with the same letter
        * */
        for (Team team : Team.values()) {
            if (("" + team).charAt(0) == c) {
                return team;
            }
        }
        return null;
    }
}
