package com.masterdevskills.hello.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.masterdevskills.hello.repo.UserRepository;
import com.masterdevskills.hello.util.LocalDateAdapter;
import com.masterdevskills.hello.util.QueryParamUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.masterdevskills.hello.util.SleepUtil.sleep;

public class UserHandlers implements HttpHandler {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserHandlers.class);
	private static final int HTTP_OK_STATUS = 200;
	private static final int HTTP_BAD_REQUEST_STATUS = 400;
	public static final String QUERY_PARAM = "delay";

	private final UserRepository userRepository;
	private final Gson gson;

	public UserHandlers(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.gson = new GsonBuilder()
						.setPrettyPrinting()
						.registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
						.create();
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		LOGGER.info("Serving user list");

		var parameters = QueryParamUtil.getQueryParameters(exchange);
		if (!parameters.containsKey(QUERY_PARAM)) {
			sendError(exchange, String.format("Param `%s` is required", QUERY_PARAM));
			return;
		}

		var delay = parameters.get(QUERY_PARAM);
		sendUsers(exchange, Integer.parseInt(delay));
	}

	private void sendError(HttpExchange exchange, String message) throws IOException {
		sendResponse(exchange, HTTP_BAD_REQUEST_STATUS, message);
	}

	private void sendUsers(HttpExchange exchange, int delay) throws IOException {
		var response = getUserInJson();

		sleep(delay);
		sendResponse(exchange, HTTP_OK_STATUS, response);
	}


	private void sendResponse(HttpExchange exchange, int status, String message) throws IOException {
		exchange.sendResponseHeaders(status, message.getBytes().length);
		exchange.getResponseHeaders().put("Content-Type", List.of("application/json"));

		try (var outputStream = exchange.getResponseBody()) {
			outputStream.write(message.getBytes());
			outputStream.flush();
		}
	}

	private String getUserInJson() {

		return gson.toJson(userRepository.findAllUsers());
	}
}
