package com.yash.repositories;

import com.yash.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yash on 3/20/17.
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {

    public List<Person> findByName(String name);

    public List<Person> findByStateAndCountry(String state,String country);

    @Query("match (p:Person{name:{0}})-[:FRIENDS_WITH]->(b:Person) return p")
    public List<Person> findPersonwithFriends(String name);

    @Query("match(p:Person{name:{0}})" +
            "call apoc.path.expand(p,FRIENDS_WITH,Person,0,3)" +
            "yield path as p" +
            "return p")
    public List<Person> findFriendsForNameAPOC(String name);
}
