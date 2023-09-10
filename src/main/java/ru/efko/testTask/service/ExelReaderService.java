package ru.efko.testTask.service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.efko.testTask.controller.ConsultantInformation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExelReaderService {

    public List<ConsultantInformation> readFile(String fileLocation) {
        Workbook workbook;
        workbook = loadWorkbook(fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        return processSheet(sheet);
    }

    private Workbook loadWorkbook(String fileLocation) {
        var extension = fileLocation.substring(fileLocation.lastIndexOf(".") + 1).toLowerCase();

        try (FileInputStream file = new FileInputStream(fileLocation)) {
            switch (extension) {
                case "xls":
                    return new HSSFWorkbook(file);
                case "xlsx":
                    return new XSSFWorkbook(file);
                default:
                    throw new RuntimeException("Unknown Excel file extension: " + extension);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<ConsultantInformation> processSheet(Sheet sheet) {
        System.out.println("Sheet: " + sheet.getSheetName());
        var divisions = new ArrayList<ConsultantInformation.DivisionDto>();
        var directions = new ArrayList<ConsultantInformation.DirectionDto>();
        var offices = new ArrayList<ConsultantInformation.OfficeDto>();
        var subDivisions = new ArrayList<ConsultantInformation.SubDivisionDto>();
        var consultants = new ArrayList<ConsultantInformation>();
        for (var row : sheet) {
            if (row.getRowNum() > 1) {
                if (row.getCell(0) != null)
                    divisions.add(new ConsultantInformation.DivisionDto(row.getRowNum(), row.getCell(0).getStringCellValue()));
                if (row.getCell(1) != null)
                    directions.add(new ConsultantInformation.DirectionDto(row.getRowNum(), row.getCell(1).getStringCellValue()));
                if (row.getCell(2) != null)
                    offices.add(new ConsultantInformation.OfficeDto(row.getRowNum(), row.getCell(2).getStringCellValue()));
                if (row.getCell(3) != null)
                    subDivisions.add(new ConsultantInformation.SubDivisionDto(row.getRowNum(), row.getCell(3).getStringCellValue()));
                if (row.getCell(4) != null) {
                    var user = new ConsultantInformation.UserDto(row.getRowNum(), row.getCell(4).getStringCellValue());
                    var consultant = new ConsultantInformation();
                    consultant.setIdRow(row.getRowNum());
                    consultant.setUser(user);
                    consultant.setNumberOfTask(row.getCell(5).getNumericCellValue());
                    consultants.add(consultant);
                }
            }
        }
        return setStructure(divisions, directions, offices, subDivisions, consultants);
    }

    private ConsultantInformation setNameDivision(ConsultantInformation user, List<ConsultantInformation.DivisionDto> divisions) {
        for (ConsultantInformation.DivisionDto division : divisions) {
            if (user.getIdRow() > division.getIdRow())
                user.setDivision(division);
        }
        return user;
    }

    private ConsultantInformation setNameDirection(ConsultantInformation user, List<ConsultantInformation.DirectionDto> directions) {
        for (ConsultantInformation.DirectionDto direction : directions) {
            if (user.getIdRow() > direction.getIdRow())
                user.setDirection(direction);
        }
        return user;
    }

    private ConsultantInformation setNameOffice(ConsultantInformation user, List<ConsultantInformation.OfficeDto> offices) {
        for (ConsultantInformation.OfficeDto office : offices) {
            if (user.getIdRow() >= office.getIdRow())
                user.setOffice(office);
        }
        return user;
    }

    private ConsultantInformation setNameSubDivision(ConsultantInformation user, List<ConsultantInformation.SubDivisionDto> subDivisions) {
        for (ConsultantInformation.SubDivisionDto subDivision : subDivisions) {
            if (user.getIdRow() == subDivision.getIdRow())
                user.setSubDivision(subDivision);
        }
        return user;
    }

    private List<ConsultantInformation> setStructure(List<ConsultantInformation.DivisionDto> divisions, List<ConsultantInformation.DirectionDto> directions,
                                                     List<ConsultantInformation.OfficeDto> offices, List<ConsultantInformation.SubDivisionDto> subDivisions,
                                                     List<ConsultantInformation> consultants) {
        return consultants
                .stream()
                .map(user -> setNameDivision(user, divisions))
                .map(user -> setNameDirection(user, directions))
                .map(user -> setNameOffice(user, offices))
                .map(user -> setNameSubDivision(user, subDivisions))
                .collect(Collectors.toList());
    }
}
