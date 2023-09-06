package ru.efko.testTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.repository.ConsultantRepository;
import ru.efko.testTask.service.mapper.ConsultantMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {
    private final ConsultantRepository repository;
    private final Sort sort = Sort.by("numberOfTask").descending();

    @Override
    public List<ConsultantDto> getAllConsultant() {
        return repository.findAll(sort)
                .stream()
                .map(ConsultantMapper::toConsultantDto)
                .collect(Collectors.toList());
    }
}
