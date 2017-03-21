package com.yash.controller;

import com.yash.domain.Person;
import com.yash.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * Created by yash on 3/20/17.
 */
@RestController
public class PersonController {

    final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public void create(){
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        personService.createPeople();
        System.out.println("Created People");
    }
    @RequestMapping(value="/find",method = RequestMethod.GET)
    public Iterable<Person> findPeople(){
    	System.out.println("################################");
        Iterable<Person> personIterable = personService.getAllPeople();
        for(Person person : personIterable){
            System.out.println("Name:" + person.getName() + " Age" + person.getAge() + "ID" + person.getId());
        }
        return personService.getAllPeople();
    }

    @RequestMapping(value="/{name}",method = RequestMethod.GET)
    public List<Person> findByname(@PathVariable String name){
        return personService.getPeople(name);
    }

    @RequestMapping(value="/country", method = RequestMethod.GET)
    public List<Person> findByStateAndCountry(@RequestParam(value="state")String state,@RequestParam(value="country") String country){
        return personService.getPeopleByCountry(state,country);
    }


}
