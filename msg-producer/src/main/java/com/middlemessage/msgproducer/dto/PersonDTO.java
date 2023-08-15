package com.middlemessage.msgproducer.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.middlemessage.msgproducer.model.Person;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;	
	private String name;	
	private Integer age;
	
	
	public PersonDTO() {
		
	}

	public PersonDTO(Long id, String name, int age) {		
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public PersonDTO(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.age = person.getAge();
	}



	@Override
	public String toString() {
		return "PersonDTO {" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
	}
}
