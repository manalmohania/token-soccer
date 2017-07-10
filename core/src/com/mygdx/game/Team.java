package com.mygdx.game;

public enum Team {
    Spain, Germany, Italy, England;

    public static String getResource(Team team){
        switch (team) {
            case Spain: return "images/tokens/spain-32.png";
            case Germany: return "images/tokens/germany-32.png";
            case Italy: return "images/tokens/italy-32.png";
            case England: return "images/tokens/england-32.png";
        }
        return null;
    }

    public static Team getTeamFromChar(char c) {
        switch (c) {
            case 'E' : return England;
            case 'G' : return Germany;
            case 'I' : return Italy;
            case 'S' : return Spain;
            default: return null;
        }
    }
}
