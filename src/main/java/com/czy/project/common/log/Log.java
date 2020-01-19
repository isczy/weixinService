package com.czy.project.common.log;

import org.apache.log4j.Logger;
import com.czy.project.common.utils.PublicUtil;

@SuppressWarnings("rawtypes")
public class Log {
	
	
	private Class c;
	private String run = "";
	private Logger log = null;

	public Log(Class c) {
		this.c = c;
		run = PublicUtil.getPropertiesValue("mylog", "run_model");// 运行模式test,run
	}

	public Log(Class c, String run) {
		this.c = c;
		this.run = run;
	}

	public void info(String msg) {
		if ("test".equals(run)) {
			if (log == null)
				log = Logger.getLogger(c);
			log.info(msg);
		}
	}

	public void info(String msg, int num) {
		if ("test".equals(run)) {
			if (log == null)
				log = Logger.getLogger(c);
			log.info(msg + " [line:" + num + "]");
		}

	}

	public void debug(String msg) {
		if (log == null)
			log = Logger.getLogger(c);
		log.debug(msg);
	}

	public void debug(String msg, int num) {
		if (log == null)
			log = Logger.getLogger(c);
		log.debug(msg + " [line:" + num + "]");
	}

	public void error(String msg) {
		if (log == null)
			log = Logger.getLogger(c);
		log.error(msg);
	}

	public void error(String msg, int num) {
		if (log == null)
			log = Logger.getLogger(c);
		log.error(msg + " [line:" + num + "]");
	}

	public int getLine() {
		Throwable throwable = new Throwable();
		StackTraceElement ste = throwable.getStackTrace()[1];
		int linenum = ste.getLineNumber();
		ste = null;
		throwable = null;
		return linenum;
	}

}
