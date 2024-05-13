package com.example.mytrainingapp.log;

import android.content.Context;
import java.io.File;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Bogdan Petrovskyi
 * @company UnitedThinkers
 * @since 2019/12/12
 */

public class LogAppender {

	protected static Context applicationContext;
	private static final String LOG_DIR = "log";
	private static final String TERMINAL_LOG = "terminal.log";
	private static final int MAX_FILE_SIZE_MB = 5;
	private static final int MAX_LOG_INDEX = 10;
	private static final String FILE_PATTERN = "%m%n";
	private static LogConfigurator logConfigurator;

	private LogAppender() {
	}

	static Logger getLogger(String clazz) {
		logConfigurator = configure();
		return Logger.getLogger(clazz);
	}

	static Logger getLogger(Class clazz, Context context) {
		applicationContext = context;
		logConfigurator = configure();
		return Logger.getLogger(clazz);
	}

	private static LogConfigurator configure() {
//		System.setProperty("log4j2.isThreadContextMapInheritable", "true");
		final LogConfigurator logConfigurator = new LogConfigurator();
		logConfigurator.setFileName(getFullLogFile());
		logConfigurator.setRootLevel(Level.ALL);
		logConfigurator.setLevel("org.apache", Level.ALL);
		logConfigurator.setUseFileAppender(true);
		logConfigurator.setUseLogCatAppender(true);
		logConfigurator.setInternalDebugging(false);
		logConfigurator.setMaxBackupSize(MAX_LOG_INDEX);
		logConfigurator.setFilePattern(FILE_PATTERN);
		logConfigurator.setMaxFileSize(1024 * 1024 * MAX_FILE_SIZE_MB);
		logConfigurator.setImmediateFlush(true);
		logConfigurator.configure();
		return logConfigurator;
	}

	public static String getLogDir() {
		return getFilesDir();
	}

	public static String getLogFile() {
		return TERMINAL_LOG;
	}

	public static String getFullLogFile() {
		return getLogDir() + File.separator + getLogFile();
	}

	public static String getFilesDir() {
		File logDir = applicationContext.getExternalFilesDir(LOG_DIR);
		if (logDir == null) {
			logDir = applicationContext.getFilesDir();
		}
		return logDir.getAbsolutePath();
	}
}
