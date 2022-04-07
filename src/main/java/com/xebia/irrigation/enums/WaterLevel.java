package com.xebia.irrigation.enums;

import java.util.Arrays;
import java.util.Optional;

public enum WaterLevel {
    LOW("LOW", 100L),
    MID("MID", 500L),
    HIGH("HIGH", 1000L);
    public final String waterlevel;
    public final long wateramount;


    WaterLevel(String waterlevel, long wateramount) {
        this.waterlevel = waterlevel;
        this.wateramount = wateramount;
    }

    public static Optional<WaterLevel> getWaterLevel(long value) {
        return Arrays.stream(WaterLevel.values())
                .filter(WaterLevel -> WaterLevel.wateramount >= (value)
                )
                .findFirst();
    }
}
