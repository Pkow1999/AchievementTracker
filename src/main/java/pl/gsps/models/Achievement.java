package pl.gsps.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Achievement {
    private String apiname;
    private int achieved;
    private int unlocktime;
    private String name;
    private String description;
}
