package ru.efko.testTask.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
public class ConsultantInformation {
    private int idRow;
    private DivisionDto division;
    private DirectionDto direction;
    private OfficeDto office;
    private SubDivisionDto subDivision;
    private UserDto user;
    private Double numberOfTask;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Builder
    public static class DivisionDto {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Builder
    public static class DirectionDto {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Builder
    public static class OfficeDto {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Builder
    public static class SubDivisionDto {
        private int idRow;
        private String name;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Builder
    public static class UserDto {
        private int idRow;
        private String name;
    }
}
