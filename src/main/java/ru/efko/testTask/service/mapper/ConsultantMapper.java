package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.controller.ConsultantExelDto;
import ru.efko.testTask.model.Consultant;
import ru.efko.testTask.model.Structure;

import java.util.List;

@UtilityClass
public class ConsultantMapper {
    public ConsultantDto toConsultantDto(Consultant consultant) {
        return ConsultantDto
                .builder()
                .userFullName(consultant.getUserFullName())
                .office(consultant.getStructure())
                .numberOfTask(consultant.getNumberOfTask())
                .build();
    }

    public Consultant toConsultant(ConsultantExelDto user) {
        Structure structure = new Structure();
        structure.setDivision(user.getDivision().getName());
        structure.setDirection(user.getDirection().getName());
        structure.setOffice(user.getOffice().getName());
        if (user.getSubDivision() != null)
            structure.setSubDivision(user.getSubDivision().getName());
        return Consultant
                .builder()
                .userFullName(user.getName())
                .numberOfTask(user.getNumberOfTask())
                .structure(structure)
                .build();
    }
}
