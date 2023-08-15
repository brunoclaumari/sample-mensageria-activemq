package com.middlemessage.msgproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsgProducerApplication implements CommandLineRunner{

	@Value("${spring.profiles.active}")
	private String text;

	public static void main(String[] args) {
		SpringApplication.run(MsgProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.print("Producer profile ativo: " + text);
	}

}
