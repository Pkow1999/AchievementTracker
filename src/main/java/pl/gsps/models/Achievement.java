package pl.gsps.models;

public record Achievement(
        String apiname,
        int achieved,
        int unlocktime
) { }
