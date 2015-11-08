package com.report.gen.util;

import com.p6spy.engine.logging.appender.P6Logger;

public class Logger4spy implements P6Logger {

	// private static final Logger logger =
	// LoggerFactory.getLogger("logger4spy");

	public String getLastEntry() {
		return null;
	}

	public void logException(Exception e) {
		e.printStackTrace();
	}

	public void logSQL(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
		if (sql.startsWith("insert") || sql.startsWith("select") || sql.startsWith("update") || sql.startsWith("delete")) {
			 System.out.println(sql);
		}
		// logger.debug(sql);
	}

	public void logText(String text) {
	}

}
