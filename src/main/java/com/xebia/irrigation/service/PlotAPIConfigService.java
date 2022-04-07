package com.xebia.irrigation.service;

import com.xebia.irrigation.model.Plot;
import com.xebia.irrigation.model.Slot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlotAPIConfigService {

    Plot createPlot(Plot plotrequest);
    Plot getPlotdetails(long id);
    List<Plot> getAllPlotDetails();
    Slot updateSlot(Long plotId,Long slotId,Slot slotrequest);
    Plot  configurePlotSlot(Long plotId,Slot slotrequest);
    Plot updatePlot(Long PlotId, Plot plotRequest);
}
