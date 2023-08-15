package com.middlemessage.msgproducer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.middlemessage.msgproducer.model.Person;



@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

   public Person getByName(String name);
      
}
