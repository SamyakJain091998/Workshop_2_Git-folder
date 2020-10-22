package com.ipl_league_analysis.workshop;

public class IPLException extends Exception {
	enum ExceptionType {
		FILE_PROBLEM, UNABLE_TO_PARSE, NO_CENSUS_DATA, CSV_FILE_INTERNAL_ISSUES
	}

	ExceptionType type;

	public IPLException(String message, String name) {
		super(message);
		this.type = ExceptionType.valueOf(name);
	}

	public IPLException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public IPLException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}
}
