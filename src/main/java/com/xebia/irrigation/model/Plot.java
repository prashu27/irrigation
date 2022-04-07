package com.xebia.irrigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "plot")
    private List<Slot> slots;

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }



}
