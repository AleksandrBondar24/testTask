package ru.efko.testTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.efko.testTask.controller.ConsultantDto;
import ru.efko.testTask.controller.ConsultantExelDto;
import ru.efko.testTask.model.Consultant;
import ru.efko.testTask.model.Structure;
import ru.efko.testTask.repository.ConsultantRepository;
import ru.efko.testTask.repository.StructureRepository;
import ru.efko.testTask.service.mapper.ConsultantMapper;
import ru.efko.testTask.service.mapper.StructureMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {
    private final ConsultantRepository repository;
    private final StructureRepository structureRepository;
    private final ExelReaderService service;
    private final Sort sort = Sort.by("numberOfTask").descending();

    @Override
    public void saveConsultant() {
        final String path = "C:/Users/alexb/Downloads/data.xlsx";
        final List<ConsultantExelDto> consultantsDto = service.readFile(path);
        final List<Structure> structures = consultantsDto
                .stream()
                .map(StructureMapper::toStructure)
                .collect(Collectors.toList());
        final List<Structure> structureList = structureRepository.saveAll(structures);
        final List<Consultant> consultants = consultantsDto
                .stream()
                .map(ConsultantMapper::toConsultant)
                .map(user -> setStructureId(user, structureList))
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

    private Consultant setStructureId(Consultant consultant, List<Structure> structures) {
        for (Structure structure : structures)
            if (structure.equals(consultant.getStructure()))
                consultant.getStructure().setId(structure.getId());
        return consultant;
    }
}
