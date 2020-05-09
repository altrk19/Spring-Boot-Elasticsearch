package com.spring.elasticsearch.controller;

import com.spring.elasticsearch.entity.Person;
import com.spring.elasticsearch.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepo personRepo;

    @PostConstruct
    public void init() {
        Person person = new Person();
        person.setName("Ali");
        person.setSurname("Turk");
        person.setAddress("address1");
        person.setBirth(Calendar.getInstance().getTime());
        person.setId("K0001");
        personRepo.save(person);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<Person>> getPerson(@PathVariable String search) {
        List<Person> personList = personRepo.findByNameLikeOrSurnameLike(search, search);
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/{search}/1")
    public ResponseEntity<List<Person>> getPerson2(@PathVariable String search) {
        List<Person> personList = personRepo.getByCustomQuery(search);
        return ResponseEntity.ok(personList);
    }
}
