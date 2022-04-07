package com.xebia.irrigation.service;

import com.xebia.irrigation.enums.IrrigationStatus;
import com.xebia.irrigation.exception.PlotNotFoundException;
import com.xebia.irrigation.model.Plot;
import com.xebia.irrigation.model.Slot;
import com.xebia.irrigation.repository.PlotIrrigationRepository;
import com.xebia.irrigation.repository.PlotSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PlotAPIConfigServiceImpl implements PlotAPIConfigService {


    @Autowired
    PlotSlotRepository plotSlotRepository;

    @Autowired
    PlotIrrigationRepository plotIrrigationRepository;


    @Override
    public Long createPlot(Plot plotrequest) {
        plotrequest.setCreateDate(ZonedDateTime.now());
        plotrequest.setLastUpdatedDate(ZonedDateTime.now());
        Plot plot = plotIrrigationRepository.save(plotrequest);
              return plot.getPlotId();
    }

    public Plot getPlotdetails(long id) {
        Optional<Plot> plot = plotIrrigationRepository.findById(id);
        return plot.orElseThrow(() -> new PlotNotFoundException("Plot doesn't exist"));
    }

    public List<Plot> getAllPlotDetails() {
        return plotIrrigationRepository.findAll();
    }

    @Override
    public Slot updateSlot(Long plotId, Long slotId, Slot slotRequest) {
        Optional<Plot> plot = plotIrrigationRepository.findById(plotId);
        if (plot.isPresent()) {
            Set<Slot> plotSlots = plot.get().getSlots();
            Optional<Slot> slot = plotSlots.stream().filter(Slot -> Slot.getSlotId() == slotId).findFirst();
            slot.orElseThrow(NoSuchElementException::new);
            Slot slottoupdate = slot.get();
            slottoupdate.setIrrigationStatus(IrrigationStatus.REQUIRED.toString());
            slottoupdate.setStartHrs(slotRequest.getStartHrs());
            slottoupdate = plotSlotRepository.save(slottoupdate);
            return slottoupdate;

        }
        return null;
    }

    @Override
    public Plot configurePlotSlot(Long plotId, Slot slotrequest) {
        log.info("Add new slot for the plot id :{}", plotId);
        Plot plot = plotIrrigationRepository.findById(plotId).orElseThrow(() -> new PlotNotFoundException("Plot doesn't Exist"));
        Set<Slot> slots = plot.getSlots();
        slots.add(slotrequest);
        plot.setSlots(slots);
        plot = plotIrrigationRepository.save(plot);
        return plot;
    }

    @Override
    public Plot updatePlot(Long plotId, Plot plotRequest) {
        log.info("update plot details for plot id {}", plotId);
        Plot plot = plotIrrigationRepository.findById(plotId).orElseThrow(() -> new PlotNotFoundException("Plot doesn't Exist"));
        plot = mapPlotObject(plotRequest, plot);
        plot = plotIrrigationRepository.save(plot);
        return plot;
    }

    private Plot mapPlotObject(Plot plotRequestObject, Plot plotStoredObect) {
        plotStoredObect.setArea(plotRequestObject.getArea());
        plotStoredObect.setCroptype(plotRequestObject.getCroptype());
        plotRequestObject.setLastUpdatedDate(ZonedDateTime.now());
        for (Slot slot : plotRequestObject.getSlots())
            if (plotStoredObect.getSlots().stream().filter(s -> s.getSlotId().equals(slot.getSlotId())).count() > 0) {
                plotStoredObect.getSlots().stream().filter(s -> s.getSlotId().equals(slot.getSlotId())).map(slot1 -> {
                    slot1.setStartHrs(slot.getStartHrs());
                    slot1.setIrrigationStatus(slot.getIrrigationStatus());
                    slot1.setLastUpdatedDate(ZonedDateTime.now());
                    slot1.setPlot(slot.getPlot());
                    return plotStoredObect;
                });

            } else {
                plotStoredObect.getSlots().add(slot);
            }
        return plotStoredObect;


    }
}