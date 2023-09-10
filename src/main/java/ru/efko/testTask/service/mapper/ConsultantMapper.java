package ru.efko.testTask.service.mapper;

import lombok.experimental.UtilityClass;
import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.controller.ConsultantInformation;
import ru.efko.testTask.model.Consultant;

@UtilityClass
public class ConsultantMapper {
    public ConsultantDto toConsultantDto(Consultant consultant) {
        return ConsultantDto
                .builder()
                .division(consultant.getDivision())
                .direction(consultant.getDirection())
                .office(consultant.getOffice())
                .subDivision(consultant.getSubDivision())
                .user(consultant.getUser())
                .numberOfTask(consultant.getNumberOfTask())
                .build();
    }

    public Consultant toConsultant(ConsultantInformation information) {
        if (information.getSubDivision() != null) {
            return Consultant
                    .builder()
                    .division(DivisionMapper.toDivision(information))
                    .direction(DirectionMapper.toDirection(information))
                    .office(OfficeMapper.toOffice(information))
                    .subDivision(SubDivisionMapper.toSubDivision(information))
                    .user(UserMapper.toUser(information))
                    .numberOfTask(information.getNumberOfTask())
                    .build();
        }
        return Consultant
                .builder()
                .division(DivisionMapper.toDivision(information))
                .direction(DirectionMapper.toDirection(information))
                .office(OfficeMapper.toOffice(information))
                .user(UserMapper.toUser(information))
                .numberOfTask(information.getNumberOfTask())
                .build();
    }
}
