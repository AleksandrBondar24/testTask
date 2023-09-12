package ru.efko.testTask;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.efko.testTask.dto.ConsultantInformation;
import ru.efko.testTask.service.ExelReaderService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ExelReaderServiceTest {
    private final ExelReaderService service;

    @Test
    void shouldReadFile() {
        var quantityConsultants = 10;
        var result = service.readFile();
        var consultant = ConsultantInformation
                .builder()
                .division(ConsultantInformation.DivisionDto
                        .builder()
                        .name("Первое")
                        .build())
                .direction(ConsultantInformation.DirectionDto
                        .builder()
                        .name("Дочернее1")
                        .build())
                .office(ConsultantInformation.OfficeDto
                        .builder()
                        .name("Дочернее 1.1")
                        .build())
                .user(ConsultantInformation.UserDto
                        .builder()
                        .name("Федоров И. А.")
                        .build())
                .build();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(quantityConsultants));
        assertThat(result.get(0).getUser().getName(), equalTo(consultant.getUser().getName()));
        assertThat(result.get(0).getDivision().getName(), equalTo(consultant.getDivision().getName()));
        assertThat(result.get(0).getDirection().getName(), equalTo(consultant.getDirection().getName()));
        assertThat(result.get(0).getOffice().getName(), equalTo(consultant.getOffice().getName()));
    }
}
