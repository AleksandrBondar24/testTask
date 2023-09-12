package ru.efko.testTask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efko.testTask.dto.ConsultantDto;
import ru.efko.testTask.service.ConsultantService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/consultant")
public class ConsultantController {

    private final ConsultantService service;

    @PostMapping
    public List<ConsultantDto> createConsultant() {
        log.info("Считан файл xlsx и добавлен в БД");
        return service.saveConsultant();
    }

    @GetMapping
    public List<ConsultantDto> getAllConsultant() {
        List<ConsultantDto> consultantDto = service.getAllConsultant();
        log.info("Получен список консультантов со структорой подразделений: {}", consultantDto);
        return consultantDto;
    }
}
