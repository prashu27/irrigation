package com.xebia.irrigation.service;

import com.xebia.irrigation.enums.IrrigationStatus;
import com.xebia.irrigation.exception.SensorDeviceNotAvaialable;
import com.xebia.irrigation.model.Slot;
import com.xebia.irrigation.repository.PlotIrrigationRepository;
import com.xebia.irrigation.repository.PlotSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private final PlotIrrigationRepository plotIrrigationRepository;

    @Autowired
    private  final PlotAPIConfigServiceImpl irrigationService;

    @Autowired
    private  final PlotSlotRepository plotSlotRepository;

    @Autowired
    private  final SensorDeviceIntegrationService sensorDeviceIntegrationService;

    @Autowired
    private final CustomExecutorService customExecutorService;



    public SchedulerService(PlotIrrigationRepository plotIrrigationRepository, PlotAPIConfigServiceImpl irrigationService, PlotSlotRepository plotSlotRepository, SensorDeviceIntegrationService sensorDeviceIntegrationService, CustomExecutorService executorService) {
        this.plotIrrigationRepository = plotIrrigationRepository;
        this.irrigationService = irrigationService;
        this.plotSlotRepository = plotSlotRepository;
        this.sensorDeviceIntegrationService = sensorDeviceIntegrationService;
        this.customExecutorService = executorService;
    }


    @Scheduled(cron="0 0/1 * * * ?")
    public void irrigatePlots(){
        log.info("Fetch plots irrigated at the scheduled time for hr :{}", ZonedDateTime.now().getHour());
      List<Slot> slots =  plotSlotRepository.findByStartHrsAndIrrigationStatus( ZonedDateTime.now().getHour(), IrrigationStatus.REQUIRED.toString());
        log.info("No of slots to be irrigated this hour :{}",slots.size());
        for(Slot slot : slots){
            customExecutorService.executorService.execute( () -> {
            try {
                sensorDeviceIntegrationService.startIrrigation(slot);
            } catch (SensorDeviceNotAvaialable sensorDeviceNotAvaialable) {
                sensorDeviceNotAvaialable.printStackTrace();
            }
        });
    };



    }

}
