package ru.efko.testTask.service;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.efko.testTask.controller.ConsultantExelDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExelReaderService {

    public List<ConsultantExelDto> readFile(String fileLocation) {
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

    private List<ConsultantExelDto> processSheet(Sheet sheet) {
        System.out.println("Sheet: " + sheet.getSheetName());
        var divisions = new ArrayList<ConsultantExelDto.Division>();
        var directions = new ArrayList<ConsultantExelDto.Direction>();
        var offices = new ArrayList<ConsultantExelDto.Office>();
        var subDivisions = new ArrayList<ConsultantExelDto.SubDivision>();
        var consultants = new ArrayList<ConsultantExelDto>();
        for (var row : sheet) {
            if (row.getRowNum() > 1) {
                if (row.getCell(0) != null)
                    divisions.add(new ConsultantExelDto.Division(row.getRowNum(), row.getCell(0).getStringCellValue()));
                if (row.getCell(1) != null)
                    directions.add(new ConsultantExelDto.Direction(row.getRowNum(), row.getCell(1).getStringCellValue()));
                if (row.getCell(2) != null)
                    offices.add(new ConsultantExelDto.Office(row.getRowNum(), row.getCell(2).getStringCellValue()));
                if (row.getCell(3) != null)
                    subDivisions.add(new ConsultantExelDto.SubDivision(row.getRowNum(), row.getCell(3).getStringCellValue()));
                if (row.getCell(4) != null) {
                    var consultant = new ConsultantExelDto();
                    consultant.setIdRow(row.getRowNum());
                    consultant.setName(row.getCell(4).getStringCellValue());
                    consultant.setNumberOfTask(row.getCell(5).getNumericCellValue());
                    consultants.add(consultant);
                }
            }
        }
        return setStructure(divisions, directions, offices, subDivisions, consultants);
    }

    private ConsultantExelDto setNameDivision(ConsultantExelDto user, List<ConsultantExelDto.Division> divisions) {
        for (ConsultantExelDto.Division division : divisions) {
            if (user.getIdRow() > division.getIdRow())
                user.setDivision(division);
        }
        return user;
    }

    private ConsultantExelDto setNameDirection(ConsultantExelDto user, List<ConsultantExelDto.Direction> directions) {
        for (ConsultantExelDto.Direction direction : directions) {
            if (user.getIdRow() > direction.getIdRow())
                user.setDirection(direction);
        }
        return user;
    }

    private ConsultantExelDto setNameOffice(ConsultantExelDto user, List<ConsultantExelDto.Office> offices) {
        for (ConsultantExelDto.Office office : offices) {
            if (user.getIdRow() >= office.getIdRow())
                user.setOffice(office);
        }
        return user;
    }

    private ConsultantExelDto setNameSubDivision(ConsultantExelDto user, List<ConsultantExelDto.SubDivision> subDivisions) {
        for (ConsultantExelDto.SubDivision subDivision : subDivisions) {
            if (user.getIdRow() == subDivision.getIdRow())
                user.setSubDivision(subDivision);
        }
        return user;
    }

    private List<ConsultantExelDto> setStructure(List<ConsultantExelDto.Division> divisions, List<ConsultantExelDto.Direction> directions,
                                                 List<ConsultantExelDto.Office> offices, List<ConsultantExelDto.SubDivision> subDivisions,
                                                 List<ConsultantExelDto> consultants) {
        return consultants
                .stream()
                .map(user -> setNameDivision(user, divisions))
                .map(user -> setNameDirection(user, directions))
                .map(user -> setNameOffice(user, offices))
                .map(user -> setNameSubDivision(user, subDivisions))
                .collect(Collectors.toList());
    }
}
