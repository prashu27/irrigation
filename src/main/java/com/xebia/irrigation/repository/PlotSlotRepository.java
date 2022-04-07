package com.xebia.irrigation.repository;

import com.xebia.irrigation.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PlotSlotRepository  extends JpaRepository<Slot,Long> {

    @Override
    Optional<Slot> findById(Long plotid);
    List<Slot> findByStartHrsAndIrrigationStatus(int hrs,String status);
    @Override
    Slot save(Slot entity);


}
