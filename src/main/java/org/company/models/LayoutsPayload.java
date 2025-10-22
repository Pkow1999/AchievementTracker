package org.company.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class LayoutsPayload {
    private String number;
    private String lastAchieved;
}
