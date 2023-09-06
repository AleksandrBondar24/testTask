package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.model.Consultant;

@UtilityClass
public class ConsultantMapper {
    public ConsultantDto toConsultantDto(Consultant consultant) {
        return ConsultantDto
                .builder()
                .user(consultant.getUser())
                .office(consultant.getStructure())
                .numberOfTask(consultant.getNumberOfTask())
                .build();
    }
}
