package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.dto.ConsultantInformation;
import ru.efko.testTask.model.User;

@UtilityClass
public class UserMapper {
    public User toUser(ConsultantInformation information) {
        return User
                .builder()
                .name(information.getUser().getName())
                .build();
    }
}
