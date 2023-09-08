package ru.efko.testTask.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.efko.testTask.model.Structure;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConsultantDto {
    private String userFullName;
    private Structure office;
    private Double numberOfTask;
}
