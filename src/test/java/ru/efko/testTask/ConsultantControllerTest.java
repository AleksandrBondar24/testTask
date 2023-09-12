package ru.efko.testTask;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.efko.testTask.controller.ConsultantController;
import ru.efko.testTask.service.ConsultantService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultantControllerTest {
    private final ConsultantService service;
    private ConsultantController consultantController;

    @BeforeEach
    public void createEnvironment() {
        consultantController = new ConsultantController(service);
    }

    @Test
    void shouldSaveConsultant() {
        var quantityConsultants = 10;
        var result = consultantController.createConsultant();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(quantityConsultants));
    }

    @Test
    void shouldGetAllConsultants() {
        consultantController.createConsultant();
        var result = consultantController.getAllConsultant();

        assertThat(result, notNullValue());
    }
}
