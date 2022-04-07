package com.xebia.irrigation.util;

import com.xebia.irrigation.enums.CropType;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
@Slf4j
public class SensorUtil {

    //decide required water based on crop type and area of the plot
    public static Long getWaterAmountPerArea(int area, String cropName){
        log.info("CROP TYPE" +CropType.getCropDetailsValue(cropName).toString());
        CropType cropType = CropType.getCropDetailsValue(cropName).orElseThrow(NoSuchElementException::new);
        return CropType.getCropDetailsValue(cropName).get().waterRequiredLtr*area;
    }



}
