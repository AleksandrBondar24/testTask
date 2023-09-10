package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantInformation;
import ru.efko.testTask.model.Office;

@UtilityClass
public class OfficeMapper {
    public Office toOffice(ConsultantInformation information) {
        return Office
                .builder()
                .name(information.getOffice().getName())
                .build();
    }
}
