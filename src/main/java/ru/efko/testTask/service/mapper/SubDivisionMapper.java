package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.dto.ConsultantInformation;
import ru.efko.testTask.model.SubDivision;

@UtilityClass
public class SubDivisionMapper {
    public SubDivision toSubDivision(ConsultantInformation information) {
        return SubDivision
                .builder()
                .name(information.getSubDivision().getName())
                .build();
    }
}
