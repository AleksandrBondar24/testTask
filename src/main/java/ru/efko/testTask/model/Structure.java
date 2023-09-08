package ru.efko.testTask.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "structures")
@Builder
public class Structure {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String division;
    private String direction;
    private String office;
    private String subDivision;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Structure structure = (Structure) o;
        return Objects.equals(division, structure.division) && Objects.equals(direction, structure.direction) &&
                Objects.equals(office, structure.office) && Objects.equals(subDivision, structure.subDivision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(division, direction, office, subDivision);
    }
}
