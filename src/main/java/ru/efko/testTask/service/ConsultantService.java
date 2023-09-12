package ru.efko.testTask.service;

import ru.efko.testTask.dto.ConsultantDto;

import java.util.List;

public interface ConsultantService {
    List<ConsultantDto> getAllConsultant();

    List<ConsultantDto> saveConsultant();
}

