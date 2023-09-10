package ru.efko.testTask.service;

import ru.efko.testTask.controller.ConsultantDto;

import java.util.List;

public interface ConsultantService {
    List<ConsultantDto> getAllConsultant();

    void saveConsultant();
}

