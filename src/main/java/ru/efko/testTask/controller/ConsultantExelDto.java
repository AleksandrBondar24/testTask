package ru.efko.testTask.controller;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ConsultantExelDto {
    private int idRow;
    private String name;
    private Double numberOfTask;
    private Division division;
    private Direction direction;
    private Office office;
    private SubDivision subDivision;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class Division {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class Direction {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class Office {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    public static class SubDivision {
        private int idRow;
        private String name;
    }
}
