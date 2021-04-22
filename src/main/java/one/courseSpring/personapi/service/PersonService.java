package one.courseSpring.personapi.service;

import lombok.AllArgsConstructor;
import one.courseSpring.personapi.dto.response.MessageResponseDTO;
import one.courseSpring.personapi.entity.Person;
import one.courseSpring.personapi.exception.PersonNotFoundException;
import one.courseSpring.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("PersonService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRepository;

    /*@Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/


    public MessageResponseDTO createPerson(Person person) {
        Person savedToPerson = personRepository.save(person);
        return createMethodResponse(savedToPerson.getId(), "Created person with ID ");
    }

    public List<Person> getAll() {
        return  personRepository.findAll();
    }

    public Person getById(Long personId) throws PersonNotFoundException {
        Person person = verifyIfExists(personId);
        return person;
    }

    public void personDelete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, Person person) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personRepository.save(person);
        return createMethodResponse(personToUpdate.getId(), "Updated person with ID ");
    }


    private MessageResponseDTO createMethodResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
