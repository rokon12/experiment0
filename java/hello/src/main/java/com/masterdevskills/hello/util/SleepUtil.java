package com.masterdevskills.hello.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(SleepUtil.class);

	public static void sleep(int delay) {
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			LOGGER.error("Unable to sleep", e);
		}
	}
}
