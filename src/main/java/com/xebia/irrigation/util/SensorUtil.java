package com.xebia.irrigation.util;

import com.xebia.irrigation.enums.CropType;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SensorUtil {

    //decide required water based on crop type and area of the plot
    public static Long getWaterAmountPerArea(int area, String cropName){
        CropType cropType = CropType.getCropDetailsValue(cropName).orElseThrow(NoSuchElementException::new);
        return CropType.getCropDetailsValue(cropName).get().waterRequiredLtr*area;
    }


}
