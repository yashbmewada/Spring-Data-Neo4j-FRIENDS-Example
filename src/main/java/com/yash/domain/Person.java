package com.yash.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;
import java.util.List;

/**
 * Created by yash on 3/20/17.
 */
@NodeEntity
public class Person {

    @GraphId
    private Long id;

    private String name;
    private int age;
    private String state;
    private String country;

    public Person() {
    }

    public Person(String name, int age, String state, String country) {
        this.name = name;
        this.age = age;
        this.state = state;
        this.country = country;
    }

    @Relationship(type = "FRIENDS_WITH", direction = "UNDIRECTED")
    private List<Person> friendsList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addFriend(Person person){
        friendsList.add(person);
    }
}
