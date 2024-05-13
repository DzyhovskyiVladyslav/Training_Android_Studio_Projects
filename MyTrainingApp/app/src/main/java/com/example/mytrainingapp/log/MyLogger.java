package com.example.mytrainingapp.log;

import android.content.Context;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/03
 */
public class MyLogger {

	public static final EventType EVENT_SUCCESS = new EventType("EVENT SUCCESS");
	public static final EventType EVENT_FAILURE = new EventType("EVENT FAILURE");
	public static final EventType EVENT_UNSPECIFIED = new EventType("EVENT UNSPECIFIED");
	private static org.apache.log4j.Logger logger;

	private MyLogger() {}

	public static void setConfiguration(Class<?> clazz, Context context) {
		logger = LogAppender.getLogger(clazz, context);
	}

	public static void fatal(Object o) {
		logger.fatal(String.valueOf(o));
	}

	public static void fatal(Object o, Throwable throwable) {
		logger.fatal(String.valueOf(o), throwable);
	}

	public static void fatal(EventType eventType, String s) {
		logger.fatal(s + " " + eventType);
	}

	public static void fatal(EventType eventType, String s, Throwable throwable) {
		logger.fatal(s + " " + eventType, throwable);
	}

	public static void error(Object o) {
		logger.error(String.valueOf(o));
	}

	public static void error(Object o, Throwable throwable) {
		logger.error(String.valueOf(o), throwable);
	}

	public static void error(EventType eventType, String s) {
		logger.error(s + " " + eventType);
	}

	public static void error(EventType eventType, String s, Throwable throwable) {
		logger.error(s + " " + eventType, throwable);
	}

	public static void warn(Object o) {
		logger.warn(String.valueOf(o));
	}

	public static void warn(Object o, Throwable throwable) {
		logger.warn(String.valueOf(o), throwable);
	}

	public static void warning(EventType eventType, String s) {
		logger.warn(s + " " + eventType);
	}

	public static void warning(EventType eventType, String s, Throwable throwable) {
		logger.warn(s + " " + eventType, throwable);
	}

	public static void info(Object o) {
		logger.info(String.valueOf(o));
	}

	public static void info(Object o, Throwable throwable) {
		logger.info(String.valueOf(o), throwable);
	}

	public static void info(EventType eventType, String s) {
		logger.info(s + " " + eventType);
	}

	public static void info(EventType eventType, String s, Throwable throwable) {
		logger.info(s + " " + eventType, throwable);
	}

	public static void debug(Object o) {
		logger.debug(String.valueOf(o));
	}

	public static void debug(Object o, Throwable throwable) {
		logger.debug(String.valueOf(o), throwable);
	}

	public static void debug(EventType eventType, String s) {
		logger.debug(s + " " + eventType);
	}

	public static void debug(EventType eventType, String s, Throwable throwable) {
		logger.debug(s + " " + eventType, throwable);
	}

	public static void trace(Object o) {
		logger.trace(String.valueOf(o));
	}

	public static void trace(Object o, Throwable throwable) {
		logger.trace(String.valueOf(o), throwable);
	}

	public static void trace(EventType eventType, String s) {
		logger.trace(s + " " + eventType);
	}

	public static void trace(EventType eventType, String s, Throwable throwable) {
		logger.trace(s + " " + eventType, throwable);
	}

	public static class EventType {
		private String type;

		public EventType(String name) {
			this.type = name;
		}

		public String toString() {
			return this.type;
		}
	}
}
