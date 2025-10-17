package pl.gsps.models;

import java.util.ArrayList;

public record Playerstats (
        String steamID,
        String gameName,
        ArrayList<Achievement> achievements,
        boolean success,
        String error
) { }
