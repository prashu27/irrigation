package com.xebia.irrigation.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CropType {
    WHEAT("WHEAT",100L),
    BAJRA("BAJRA",200L),
    CORN("CORN",300L),
    BARLEY("BARLEY",400L);
    public final long waterRequiredLtr;
    public final String cropName;
    CropType( String cropName,Long waterRequiredLtr) {
        this.cropName=cropName;
        this.waterRequiredLtr = waterRequiredLtr;


    }
    public static Optional<CropType> getCropDetailsValue(String value) {
        return Arrays.stream(CropType.values())
                .filter(accStatus -> accStatus.cropName.equals(value)
                        )
                .findFirst();
    }

}
