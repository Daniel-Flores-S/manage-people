package one.courseSpring.personapi.controller;

import lombok.AllArgsConstructor;


import one.courseSpring.personapi.dto.response.MessageResponseDTO;
import one.courseSpring.personapi.entity.Person;
import one.courseSpring.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return  MessageResponseDTO
                .builder()
                .message("Created person with ID: "+ savedPerson.getId())
                .build();
    }


}
