package com.xebia.irrigation.service;
import com.sun.xml.bind.v2.TODO;
import com.xebia.irrigation.enums.IrrigationStatus;
import com.xebia.irrigation.enums.WaterLevel;
import com.xebia.irrigation.exception.SensorDeviceNotAvaialable;
import com.xebia.irrigation.model.Plot;
import com.xebia.irrigation.model.SensorDevice;
import com.xebia.irrigation.model.Slot;
import com.xebia.irrigation.repository.PlotSlotRepository;
import com.xebia.irrigation.util.SensorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.Optional;

@Slf4j
@Service
public class SensorDeviceIntegrationService {

   private  final SensorDevice sensorDevice;

   @Autowired
   public final PlotSlotRepository repository;

    public SensorDeviceIntegrationService(SensorDevice sensorDevice, PlotSlotRepository repository) {
        this.sensorDevice = sensorDevice;
        this.repository = repository;
    }

   /* this method will evaluate the water level (Mid/LOW/HIGH) using the crops type(BAJRA/CORN etc) and area of the plot*/
    public void startIrrigation(Slot slot) throws SensorDeviceNotAvaialable {
       log.info("Irrigation has been started  :" +slot.getSlotId());
        Plot plot=slot.getPlot();
        Optional<WaterLevel> waterLevel=WaterLevel.getWaterLevel(SensorUtil.getWaterAmountPerArea(plot.getArea(),plot.getCroptype()));
        sensorDevice.setWaterLevel(WaterLevel.LOW.toString());
        if(isSensorDeviceAvaiable())
       {
           log.info("Irrigation request has been processed by sensor");
           slot.setIrrigationStatus(IrrigationStatus.COMPLETED.toString());
           slot.setLastUpdatedDate(ZonedDateTime.now());
           repository.save(slot);

       }
       else{
           log.info("Sensor is not Available!!");
       }


    }


    @Retryable(value = { SensorDeviceNotAvaialable.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Boolean isSensorDeviceAvaiable() throws SensorDeviceNotAvaialable {
        //TODO: Sensor not avaiable derived from Sensor Integration
          return true;
      }



    @Recover
    public String isSensorDeviceAvaiableFallback(SensorDeviceNotAvaialable e) {
return "Sensor retry expired!!!";
    }

}


