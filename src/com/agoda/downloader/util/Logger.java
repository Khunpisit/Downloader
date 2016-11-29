package com.agoda.downloader.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;

public class Logger {
	private static final String INFO_PREFIX = "[info] ";
	private static final String ERROR_PREFIX = "[error] ";
	
	public static void info(String pattern, Object... arguments) {
		String msg = MessageFormat.format(pattern, arguments);
		info(msg);
	}

	public static void info(String msg) {
		System.out.println(INFO_PREFIX + msg);
	}
	
	public static void debug(String pattern, Object... arguments) {
		String msg = MessageFormat.format(pattern, arguments);
		debug(msg);
	}	
	
	public static void debug(String msg) {
		System.out.println(msg);
	}
	
	public static void error(String msg) {
		System.out.println(ERROR_PREFIX + msg);
	}
	
	public static void error(String msg, Exception ex) {
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		System.out.println(ERROR_PREFIX + errors.toString());
	}

}
