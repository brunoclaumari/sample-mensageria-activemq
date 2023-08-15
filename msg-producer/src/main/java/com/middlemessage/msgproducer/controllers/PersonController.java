package com.middlemessage.msgproducer.controllers;

import java.util.List;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.middlemessage.msgproducer.dto.PersonDTO;
import com.middlemessage.msgproducer.jms.Producer;
import com.middlemessage.msgproducer.model.Person;
import com.middlemessage.msgproducer.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

	private final Producer producer;

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public ResponseEntity<List<Person>> getAll() {
		List<Person> list = personRepository.findAll();

		return ResponseEntity.ok().body(list);
	}

	/* @PostMapping(value = "/salvar")
	public String save(@RequestParam("name") String name, @RequestParam("age") int age)
			throws JMSException, NamingException {
		Person person = Person.builder()
				.name(name)
				.age(age)
				.build();
		producer.send(person);
		return "/cadastra-pessoas";
	} */

	@PostMapping(value = "/salvar")
	public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO dto)
			throws JMSException, NamingException {
		Person person = Person.builder()
				.name(dto.getName())
				.age(dto.getAge())
				.build();
		producer.send(person);
		return ResponseEntity.ok().body(new PersonDTO(person));
	}

}
