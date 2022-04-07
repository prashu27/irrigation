package com.xebia.irrigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "plots")
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long plotId;
    @NotNull(message = "area is mandatory")
    private int area;
    @NotBlank(message = "croptype is mandatory")
    private String croptype;
    private int waterRequired;
    private int temperature;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdatedDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "plot", cascade = CascadeType.MERGE)
    private Set<Slot> slots= new HashSet<>();

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
        for (Slot b : slots) {
            b.setPlot(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plot plot = (Plot) o;
        return plotId == plot.plotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(plotId);
    }
}
