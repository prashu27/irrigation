package com.xebia.irrigation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Table(name = "slots")
@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slotGenerator")
    @NonNull
    Long slotId;
    @NonNull
    int startHrs;
    @NonNull
    String irrigationStatus;
    ZonedDateTime lastUpdatedDate;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "plotId", nullable = false)
    @JsonIgnore
    private Plot plot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(slotId, slot.slotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotId);
    }
}
