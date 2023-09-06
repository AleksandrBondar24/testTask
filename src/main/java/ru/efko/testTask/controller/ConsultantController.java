package ru.efko.testTask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<ConsultantDto> getAllConsultant() {
        return service.getAllConsultant();
    }
}
