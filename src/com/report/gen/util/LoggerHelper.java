package com.report.gen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHelper {
	private static Logger logger = null;

	public static Logger getLogger(Class ClassName) {
		logger = LoggerFactory.getLogger(ClassName);
		return logger;
	}
	
	
	public void debug(String content)
	{
		if (logger.isDebugEnabled()) {
			logger.debug(content);
		}
	}
	
	public void error(String content) {
		if (logger.isErrorEnabled()) {
			logger.error(content);
		}
	}
	
	public void warn(String content) {
		if (logger.isWarnEnabled()) {
			logger.warn(content);
		}
	}
	
	public void info(String content) {
		if (logger.isInfoEnabled()) {
			logger.info(content);
		}
	}
}
