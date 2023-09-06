package ru.efko.testTask.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.efko.testTask.model.Structure;
import ru.efko.testTask.model.User;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConsultantDto {
    private User user;
    private Structure office;
    private Double numberOfTask;
}
