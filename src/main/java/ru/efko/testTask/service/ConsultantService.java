package ru.efko.testTask.service;

import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.model.Consultant;

import java.util.List;

public interface ConsultantService {
    List<ConsultantDto> getAllConsultant();

    void saveConsultant();
}

