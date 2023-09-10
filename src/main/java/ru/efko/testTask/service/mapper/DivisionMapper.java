package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantInformation;
import ru.efko.testTask.model.Division;

@UtilityClass
public class DivisionMapper {
    public Division toDivision(ConsultantInformation information) {
        return Division
                .builder()
                .name(information.getDivision().getName())
                .build();
    }
}
