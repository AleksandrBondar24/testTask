package ru.efko.testTask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.efko.testTask.model.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ConsultantDto {
    private Division division;
    private Direction direction;
    private Office office;
    private SubDivision subDivision;
    private User user;
    private Double numberOfTask;
}
