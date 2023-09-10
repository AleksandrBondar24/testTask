package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantInformation;
import ru.efko.testTask.model.Direction;

@UtilityClass
public class DirectionMapper {
    public Direction toDirection(ConsultantInformation information) {
        return Direction
                .builder()
                .name(information.getDirection().getName())
                .build();
    }
}
