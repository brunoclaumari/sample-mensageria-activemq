package com.middlemessage.msgproducer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Builder
@Table(name="tb_person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;	
	private String name;	
	private Integer age;
	
	
	public Person() {
		
	}

	public Person(Long id, String name, int age) {		
		this.id = id;
		this.name = name;
		this.age = age;
	}



	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
	}
}
