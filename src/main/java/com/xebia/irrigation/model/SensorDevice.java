package com.xebia.irrigation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Builder
@Slf4j
@NoArgsConstructor
@Component
public class SensorDevice {
   private Boolean isAvaiable;
   private String waterLevel;
   private int duration;
}
