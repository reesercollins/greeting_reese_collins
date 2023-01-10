package com.example;

import jakarta.annotation.PreDestroy;
import org.hsqldb.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreetingApplication {

	public static Server server;

	public static void main(String[] args) {
		server = new Server();
		server.setDatabaseName(0, "maindb");
		server.setDatabasePath(0, "mem:maindb");
		server.setPort(9001);
		server.start();
		SpringApplication.run(GreetingApplication.class, args);
	}

	@PreDestroy
	public static void shutdownServer() {
		server.stop();
	}

}
