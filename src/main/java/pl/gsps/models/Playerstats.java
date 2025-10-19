package pl.gsps.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;

@Data
@Builder
@Jacksonized
public class Playerstats {
    private String steamID;
    private String gameName;
    private ArrayList<Achievement> achievements;
    private boolean success;
    private String error;
}
