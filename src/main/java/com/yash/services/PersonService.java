package com.yash.services;

import com.yash.domain.Person;
import com.yash.repositories.PersonRepository;
import org.neo4j.ogm.session.Neo4jSession;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yash on 3/20/17.
 */
@Service
public class PersonService{

    @Autowired
    PersonRepository personRepository;

    @Autowired
    Session neo4jSession;

    public void createPeople(){
        Person yash = new Person("Yash Mewada", 21 , "GJ" , "IN");
        Person kalpesh = new Person("Kalpesh Kundanani", 21, "GJ", "IN");
        Person heli = new Person("Heli Patel", 21 , "HYD", "IN" );
        Person auzrielle = new Person("Auzi Rielle", 22 , "LA", "USA");
        Person vishal = new Person("Vishal Katharotiya", 21, "GJ","IN");

        
        personRepository.save(yash);
        personRepository.save(kalpesh);
        personRepository.save(heli);
        personRepository.save(auzrielle);
        personRepository.save(vishal);
        
        yash.addFriend(kalpesh);
        kalpesh.addFriend(heli);
        yash.addFriend(vishal);
        vishal.addFriend(heli);        
        vishal.addFriend(kalpesh);
        
        personRepository.save(yash);
        personRepository.save(kalpesh);
        personRepository.save(heli);
        personRepository.save(auzrielle);
        personRepository.save(vishal);

    }

    public List<Person> getPeople(String name){
        return personRepository.findByName(name);
    }

    public List<Person> getPeopleByCountry(String state,String country){
        return personRepository.findByStateAndCountry(state,country);
    }

    public Iterable<Person> getAllPeople(){
        return personRepository.findAll(1);
    }
}
