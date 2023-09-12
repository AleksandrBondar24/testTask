package ru.efko.testTask;

import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.efko.testTask.model.*;
import ru.efko.testTask.repository.*;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
public class ConsultantRepositoryTest {
    private final TestEntityManager em;
    private final ConsultantRepository consultantRepository;
    private final DivisionRepository divisionRepository;
    private final DirectionRepository directionRepository;
    private final OfficeRepository officeRepository;
    private final SubDivisionRepository subDivisionRepository;
    private final UserRepository userRepository;
    private Consultant consultant;

    @Autowired
    public ConsultantRepositoryTest(TestEntityManager em, ConsultantRepository consultantRepository,
                                    DivisionRepository divisionRepository, DirectionRepository directionRepository,
                                    OfficeRepository officeRepository, SubDivisionRepository subDivisionRepository,
                                    UserRepository userRepository) {
        this.em = em;
        this.consultantRepository = consultantRepository;
        this.divisionRepository = divisionRepository;
        this.directionRepository = directionRepository;
        this.officeRepository = officeRepository;
        this.subDivisionRepository = subDivisionRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void createEnvironment() {
        var division = Division
                .builder()
                .name("Первое")
                .build();
        divisionRepository.save(division);
        var direction = Direction
                .builder()
                .name("Дочернее2")
                .build();
        directionRepository.save(direction);
        var office = Office
                .builder()
                .name("Дочернее 2.1")
                .build();
        officeRepository.save(office);
        var subDivision = SubDivision
                .builder()
                .name("Дочернее 2.1.1")
                .build();
        subDivisionRepository.save(subDivision);
        var user = User
                .builder()
                .name("Петров И. А.")
                .build();
        userRepository.save(user);
        consultant = Consultant
                .builder()
                .division(division)
                .direction(direction)
                .office(office)
                .subDivision(subDivision)
                .user(user)
                .numberOfTask(0.35)
                .build();
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(em);
    }

    @Test
    void verifySaveAll() {
        var list = new ArrayList<Consultant>();
        list.add(consultant);
        var result = consultantRepository.saveAll(list);

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getDivision().getName(), equalTo(consultant.getDivision().getName()));
    }

    @Test
    void verifyFindAll() {
        var list = new ArrayList<Consultant>();
        list.add(consultant);
        consultantRepository.saveAll(list);
        var result = consultantRepository.findAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(1));
    }
}
