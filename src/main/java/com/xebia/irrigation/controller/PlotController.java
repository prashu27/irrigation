package com.xebia.irrigation.controller;

import com.xebia.irrigation.model.Plot;
import com.xebia.irrigation.model.Slot;
import com.xebia.irrigation.service.PlotAPIConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/plots/")
public class PlotController {

    @Autowired
    PlotAPIConfigService plotAPIConfigService;


    @PostMapping("/new")
    public ResponseEntity<Long> createPlot(@Valid @RequestBody Plot plot){
       Long plotId= plotAPIConfigService.createPlot(plot);
       return new ResponseEntity<>(plotId,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{plotid}")
    public ResponseEntity<Plot> getPlotdetails(@PathVariable( "plotid")  long id){
        Plot plot= plotAPIConfigService.getPlotdetails(id);
        if(plot==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
        return new ResponseEntity<>(plot,HttpStatus.OK);
    }
    @PostMapping("/{plotid}/newslot")
    public ResponseEntity<Plot> configureSlot(@PathVariable( "plotid")  long plotId,@Valid @RequestBody Slot slotRequest){
        Plot plot= plotAPIConfigService.configurePlotSlot(plotId,slotRequest);
        if(plot==null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else
            return new ResponseEntity<>(plot,HttpStatus.ACCEPTED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Plot>>getPlotdetails(){
        List<Plot> plots= plotAPIConfigService.getAllPlotDetails();
        if(plots.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
        return new ResponseEntity<>(plots,HttpStatus.OK);
    }

 @PutMapping("/{plotid}")
 public ResponseEntity<Plot> updatePlot(@PathVariable( "plotid")  long plotId,@Valid @RequestBody Plot plotRequest ){
     Plot plot= plotAPIConfigService.updatePlot(plotId,plotRequest);
     if(plot!=null)
     return new ResponseEntity<>(plot,HttpStatus.OK);
     else
        return new ResponseEntity<>(plot,HttpStatus.INTERNAL_SERVER_ERROR);
 }

}
