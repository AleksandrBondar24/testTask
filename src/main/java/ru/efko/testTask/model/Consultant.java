package ru.efko.testTask.model;

import lombok.*;

import javax.persistence.*;

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
    private String userFullName;
    private Double numberOfTask;
    @ManyToOne
    @JoinColumn(name = "structure_id", referencedColumnName = "id")
    private Structure structure;
}
