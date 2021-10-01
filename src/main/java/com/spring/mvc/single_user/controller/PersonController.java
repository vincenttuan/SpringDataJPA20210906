package com.spring.mvc.single_user.controller;

import com.spring.mvc.single_user.entities.Person;
import com.spring.mvc.single_user.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    // uri: /mvc/person/name?name=vin
    @GetMapping(value = {"/name"})
    @ResponseBody
    public Person getByName(@RequestParam("name") String name) {
        return personRepository.findByLastName(name);
    }
    
}
