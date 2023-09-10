package ru.efko.testTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.controller.ConsultantInformation;
import ru.efko.testTask.model.*;
import ru.efko.testTask.repository.*;
import ru.efko.testTask.service.mapper.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {
    private final ConsultantRepository repository;
    private final DivisionRepository divisionRepository;
    private final DirectionRepository directionRepository;
    private final OfficeRepository officeRepository;
    private final SubDivisionRepository subDivisionRepository;
    private final UserRepository userRepository;
    private final ExelReaderService service;
    private final Sort sort = Sort.by("numberOfTask").descending();

    @Override
    public void saveConsultant() {
        final String path = "C:/Users/alexb/Downloads/data.xlsx";
        final List<ConsultantInformation> consultantsInf = service.readFile(path);
        final List<Division> divisionList = saveDivision(consultantsInf);
        final List<Direction> directionList = saveDirection(consultantsInf);
        final List<Office> officeList = saveOffice(consultantsInf);
        final List<SubDivision> subDivisionList = saveSubDivision(consultantsInf);
        final List<User> userList = saveUser(consultantsInf);

        final List<Consultant> consultants = consultantsInf
                .stream()
                .map(ConsultantMapper::toConsultant)
                .map(user -> setModelsIds(user, divisionList, directionList, officeList, subDivisionList, userList))
                .collect(Collectors.toList());
        repository.saveAll(consultants);
    }

    @Override
    public List<ConsultantDto> getAllConsultant() {
        return repository.findAll(sort)
                .stream()
                .map(ConsultantMapper::toConsultantDto)
                .collect(Collectors.toList());
    }

    private Consultant setModelsIds(Consultant consultant, List<Division> divisionList, List<Direction> directionList,
                                    List<Office> officeList, List<SubDivision> subDivisionList, List<User> userList) {
        for (Division division : divisionList)
            if (division.getName().equals(consultant.getDivision().getName()))
                consultant.getDivision().setId(division.getId());
        for (Direction direction : directionList)
            if (direction.getName().equals(consultant.getDirection().getName()))
                consultant.getDirection().setId(direction.getId());
        for (Office office : officeList)
            if (office.getName().equals(consultant.getOffice().getName()))
                consultant.getOffice().setId(office.getId());
        if (consultant.getSubDivision() != null)
            for (SubDivision subDivision : subDivisionList)
                if (subDivision.getName().equals(consultant.getSubDivision().getName()))
                    consultant.getSubDivision().setId(subDivision.getId());
        for (User user : userList)
            if (user.getName().equals(consultant.getUser().getName()))
                consultant.getUser().setId(user.getId());
        return consultant;
    }

    private List<Division> saveDivision(List<ConsultantInformation> consultantsInf) {
        final List<Division> divisions = consultantsInf
                .stream()
                .map(DivisionMapper::toDivision)
                .collect(Collectors.toList());
        return divisionRepository.saveAll(divisions);
    }

    private List<Direction> saveDirection(List<ConsultantInformation> consultantsInf) {
        final List<Direction> directions = consultantsInf
                .stream()
                .map(DirectionMapper::toDirection)
                .collect(Collectors.toList());
        return directionRepository.saveAll(directions);
    }

    private List<Office> saveOffice(List<ConsultantInformation> consultantsInf) {
        final List<Office> offices = consultantsInf
                .stream()
                .map(OfficeMapper::toOffice)
                .collect(Collectors.toList());
        return officeRepository.saveAll(offices);
    }

    private List<SubDivision> saveSubDivision(List<ConsultantInformation> consultantsInf) {
        final List<SubDivision> subDivisions = consultantsInf
                .stream()
                .filter(user -> user.getSubDivision() != null)
                .map(SubDivisionMapper::toSubDivision)
                .collect(Collectors.toList());
        return subDivisionRepository.saveAll(subDivisions);
    }

    private List<User> saveUser(List<ConsultantInformation> consultantsInf) {
        final List<User> users = consultantsInf
                .stream()
                .map(UserMapper::toUser)
                .collect(Collectors.toList());
        return userRepository.saveAll(users);
    }
}
