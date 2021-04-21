package one.courseSpring.personapi.service;

import one.courseSpring.personapi.dto.response.MessageResponseDTO;
import one.courseSpring.personapi.entity.Person;
import one.courseSpring.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PersonService")
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return  MessageResponseDTO
                .builder()
                .message("Created person with ID: "+ savedPerson.getId())
                .build();
    }

}
