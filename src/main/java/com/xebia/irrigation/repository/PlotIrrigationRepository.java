package com.xebia.irrigation.repository;

import com.xebia.irrigation.model.Plot;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PlotIrrigationRepository extends JpaRepository<Plot, Long> {

    @Override
    List<Plot> findAll();

    @Override
    Plot save(Plot entity);

    @Override
    Optional<Plot> findById(Long aLong);

    @Override
    List<Plot> findAll(Sort sort);
}
