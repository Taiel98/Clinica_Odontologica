package com.backend.digitalhouse.Clinica_Odontologica;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);


	public static void main(String[] args) {

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		LOGGER.info("La aplicación está corriendo (^~^) ¬(°o°)/");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
