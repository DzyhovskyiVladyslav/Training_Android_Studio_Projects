package com.example.mytrainingapp.log;

import com.example.mytrainingapp.MyException;
import java.io.IOException;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.LogLog;

/**
 * @author Sergey Sereda
 * @company United Thinkers
 * @since 2020/06/15
 */

public class LogConfigurator {
	private Level rootLevel = Level.DEBUG;
	private String filePattern = "%d - [%p::%c::%C] - %m%n";
	private String logCatPattern = "%m%n";
	private String fileName = "android-log4j.log";
	private int maxBackupSize = 5;
	private long maxFileSize = 512 * 1024L;
	private boolean immediateFlush = true;
	private boolean useLogCatAppender = true;
	private boolean useFileAppender = true;
	private boolean resetConfiguration = true;
	private boolean internalDebugging = false;
	private RollingFileAppender rollingFileAppender;

	public LogConfigurator() {
	}

	public LogConfigurator(final String fileName) {
		setFileName(fileName);
	}

	public LogConfigurator(final String fileName, final Level rootLevel) {
		this(fileName);
		setRootLevel(rootLevel);
	}

	public LogConfigurator(final String fileName, final Level rootLevel, final String filePattern) {
		this(fileName);
		setRootLevel(rootLevel);
		setFilePattern(filePattern);
	}

	public LogConfigurator(final String fileName, final int maxBackupSize,
						   final long maxFileSize, final String filePattern, final Level rootLevel) {
		this(fileName, rootLevel, filePattern);
		setMaxBackupSize(maxBackupSize);
		setMaxFileSize(maxFileSize);
	}

	public void configure() {
		final Logger root = Logger.getRootLogger();
		if (isResetConfiguration()) {
			LogManager.getLoggerRepository().resetConfiguration();
		}
		LogLog.setInternalDebugging(isInternalDebugging());
		if (isUseFileAppender()) {
			this.rollingFileAppender = configureFileAppender();
		}
		root.setLevel(getRootLevel());
	}

	public void setLevel(final String loggerName, final Level level) {
		Logger.getLogger(loggerName).setLevel(level);
	}

	public void rollOver() {
		if (rollingFileAppender != null) {
			rollingFileAppender.rollOver();
		}
	}

	private RollingFileAppender configureFileAppender() {
		final Logger root = Logger.getRootLogger();
		final Layout fileLayout = new PatternLayout(getFilePattern());
		try {
			rollingFileAppender = new RollingFileAppender(fileLayout, getFileName());
		} catch (final IOException e) {
			//@suppress: ошибка не будет залоггирована в файл, и не отобразится пользователю
			MyException.error(e);
		}
		rollingFileAppender.setMaxBackupIndex(getMaxBackupSize());
		rollingFileAppender.setMaximumFileSize(getMaxFileSize());
		rollingFileAppender.setImmediateFlush(isImmediateFlush());
		root.addAppender(rollingFileAppender);
		return rollingFileAppender;
	}

	public Level getRootLevel() {
		return rootLevel;
	}

	public void setRootLevel(final Level level) {
		this.rootLevel = level;
	}

	public String getFilePattern() {
		return filePattern;
	}

	public void setFilePattern(final String filePattern) {
		this.filePattern = filePattern;
	}

	public String getLogCatPattern() {
		return logCatPattern;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public int getMaxBackupSize() {
		return maxBackupSize;
	}

	public void setMaxBackupSize(final int maxBackupSize) {
		this.maxBackupSize = maxBackupSize;
	}

	public long getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(final long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public boolean isImmediateFlush() {
		return immediateFlush;
	}

	public void setImmediateFlush(final boolean immediateFlush) {
		this.immediateFlush = immediateFlush;
	}

	public boolean isUseFileAppender() {
		return useFileAppender;
	}

	public void setUseFileAppender(final boolean useFileAppender) {
		this.useFileAppender = useFileAppender;
	}

	public boolean isUseLogCatAppender() {
		return useLogCatAppender;
	}

	public void setUseLogCatAppender(final boolean useLogCatAppender) {
		this.useLogCatAppender = useLogCatAppender;
	}

	public boolean isResetConfiguration() {
		return resetConfiguration;
	}

	public void setInternalDebugging(boolean internalDebugging) {
		this.internalDebugging = internalDebugging;
	}

	public boolean isInternalDebugging() {
		return internalDebugging;
	}

}

