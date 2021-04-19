package one.courseSpring.personapi.service;

import one.courseSpring.personapi.dto.request.PersonDTO;
import one.courseSpring.personapi.dto.response.MessageResponseDTO;
import one.courseSpring.personapi.entity.Person;
import one.courseSpring.personapi.mapper.PersonMapper;
import one.courseSpring.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return  MessageResponseDTO
                .builder()
                .message("Created person with ID: "+ savedPerson.getId())
                .build();
    }
}
