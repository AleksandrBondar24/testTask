package ru.efko.testTask.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "consultants")
@Builder
public class Consultant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private Division division;
    @ManyToOne
    @JoinColumn(name = "direction_id", referencedColumnName = "id")
    private Direction direction;
    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    private Office office;
    @ManyToOne
    @JoinColumn(name = "sub_division_id", referencedColumnName = "id")
    private SubDivision subDivision;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Double numberOfTask;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultant that = (Consultant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
