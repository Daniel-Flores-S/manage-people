package one.courseSpring.personapi.controller;

import one.courseSpring.personapi.dto.request.PersonDTO;
import one.courseSpring.personapi.dto.response.MessageResponseDTO;
import one.courseSpring.personapi.entity.Person;
import one.courseSpring.personapi.exception.PersonNotFoundException;
import one.courseSpring.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid Person person) {
        return  personService.createPerson(person);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> selectAll() {
        return personService.getAll();
    }

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public Person getById(@PathVariable Long personId) throws PersonNotFoundException {
        return personService.getById(personId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        personService.personDelete(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody  @Valid Person person) throws PersonNotFoundException {
        return personService.updateById(id, person);
    }



}
