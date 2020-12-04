package com.masterdevskills.hello;

import com.masterdevskills.hello.handlers.UserHandlers;
import com.masterdevskills.hello.repo.User;
import com.masterdevskills.hello.repo.UserRepository;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;


public class AppServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppServer.class);

	private static final int PORT = 8080;

	public static void main(String[] args) throws IOException {
		var server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
		server.createContext("/users", new UserHandlers(new UserRepository()));

		var factory = Thread.builder().virtual()
						.name("virtual-", 0)
						.factory();
		var executorService = Executors.newThreadExecutor(factory);

		server.setExecutor(executorService); // project loom
		server.start();

		LOGGER.info("Server is started and listening on port {}", PORT);
	}
}
