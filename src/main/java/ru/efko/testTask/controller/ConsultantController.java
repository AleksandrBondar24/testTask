package ru.efko.testTask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.efko.testTask.service.ConsultantService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/consultant")
public class ConsultantController {

    private final ConsultantService service;

    @PostMapping
    public void createConsultant() {
        service.saveConsultant();
        log.debug("Считан файл xlsx и добавлен в БД");
    }

    @GetMapping
    public List<ConsultantDto> getAllConsultant() {
        List<ConsultantDto> consultantDto = service.getAllConsultant();
        log.debug("Получен список консультантов со структорой подразделений: {}", consultantDto);
        return consultantDto;
    }
}
