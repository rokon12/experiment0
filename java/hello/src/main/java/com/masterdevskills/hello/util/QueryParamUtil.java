package com.masterdevskills.hello.util;

import com.sun.net.httpserver.HttpExchange;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryParamUtil {

	private static final String AND_DELIMITER = "&";
	private static final String EQUAL_DELIMITER = "=";

	public static Map<String, String> getQueryParameters(HttpExchange httpExchange) {
		var queryString = httpExchange.getRequestURI().getQuery();
		if (queryString == null) {
			return Map.of();
		}
		var queries = queryString.split(AND_DELIMITER);

		return Stream.of(queries)
						.map(QueryParamUtil::getKeyValuePair)
						.collect(Collectors.toMap(pair -> pair[0], pair -> pair[1]));
	}

	private static String[] getKeyValuePair(String query) {
		String[] pair = query.split(EQUAL_DELIMITER);
		if (pair.length > 1) {
			return new String[]{pair[0], pair[1]};
		} else {
			return new String[]{pair[0], ""};
		}
	}
}
