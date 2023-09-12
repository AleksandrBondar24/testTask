package ru.efko.testTask;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import ru.efko.testTask.repository.*;
import ru.efko.testTask.service.ConsultantService;
import ru.efko.testTask.service.ConsultantServiceImpl;
import ru.efko.testTask.service.ExelReaderService;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConsultantServiceImplTest {
    private ConsultantService service;
    @Mock
    private final ConsultantRepository consultantRepository;
    @Mock
    private final DivisionRepository divisionRepository;
    @Mock
    private final DirectionRepository directionRepository;
    @Mock
    private final OfficeRepository officeRepository;
    @Mock
    private final SubDivisionRepository subDivisionRepository;
    @Mock
    private final UserRepository userRepository;
    private final ExelReaderService exelReaderService;

    @BeforeEach
    public void createEnvironment() {
        service = new ConsultantServiceImpl(consultantRepository, divisionRepository, directionRepository,
                officeRepository, subDivisionRepository, userRepository, exelReaderService);
    }

    @Test
    void shouldSaveStructureAndConsultant() {
        when(consultantRepository.saveAll(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(divisionRepository.saveAll(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(directionRepository.saveAll(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(officeRepository.saveAll(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(subDivisionRepository.saveAll(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(userRepository.saveAll(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var quantityConsultants = 10;
        var result = service.saveConsultant();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(quantityConsultants));
        verify(divisionRepository, times(1)).saveAll(any());
        verify(directionRepository, times(1)).saveAll(any());
        verify(officeRepository, times(1)).saveAll(any());
        verify(subDivisionRepository, times(1)).saveAll(any());
        verify(userRepository, times(1)).saveAll(any());
        verify(consultantRepository, times(1)).saveAll(any());
    }

    @Test
    void shouldFindAllConsultant() {
        when(consultantRepository.findAll())
                .thenReturn(Collections.emptyList());
        var sort = Sort.by("numberOfTask").descending();

        var result = service.getAllConsultant();

        assertThat(result, notNullValue());
        assertThat("isEmpty", result.isEmpty());
        verify(consultantRepository, times(1)).findAll(sort);
    }
}
