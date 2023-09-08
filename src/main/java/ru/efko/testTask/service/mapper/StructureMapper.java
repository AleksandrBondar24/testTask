package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantExelDto;
import ru.efko.testTask.model.Structure;

@UtilityClass
public class StructureMapper {

    public Structure toStructure(ConsultantExelDto user) {
        Structure structure = new Structure();
        structure.setDivision(user.getDivision().getName());
        structure.setDirection(user.getDirection().getName());
        structure.setOffice(user.getOffice().getName());
        if (user.getSubDivision() != null)
            structure.setSubDivision(user.getSubDivision().getName());
        return structure;
    }
}
