package com.example.comarch;

import com.example.comarch.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling

public class ComarchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComarchApplication.class, args);
	}



}
