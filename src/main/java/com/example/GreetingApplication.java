package com.example;

import org.hsqldb.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreetingApplication {

	public static void main(String[] args) {
		Server server = new Server();
		server.setDatabaseName(0, "maindb");
		server.setDatabasePath(0, "mem:maindb");
		server.setPort(9001);
		server.start();
		SpringApplication.run(GreetingApplication.class, args);
	}

}
