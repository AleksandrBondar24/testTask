package ru.efko.testTask.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class DataExelDto {
    private String division;
    private String direction;
    private String office;
    private String subDivision;
    private String userFullName;
    private Double numberOfTask;
}
