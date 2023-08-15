package com.middlemessage.msgconsumer.jms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.middlemessage.msgconsumer.model.Person;
import com.middlemessage.msgconsumer.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Consumer {
	
	private List<Person> persons = new ArrayList<>();	
	
	@Autowired
	private PersonRepository personRepository;
	
	@JmsListener(destination = "fila.pessoa")
	public void listen(String mensagem) {
		System.out.println(mensagem);
		
		Person person = new Person();
		try {
			Gson gson = new Gson();
			person = gson.fromJson(mensagem, Person.class);
			System.out.println(person);
			personRepository.save(person);
			persons.add(person);
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um erro: " + e.getMessage());
		}
		
	}

}
