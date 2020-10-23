package com.practice.capgemini;

public class CSVBuilderException extends Throwable {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE
    }
    ExceptionType exceptionType;

    public CSVBuilderException(String message,ExceptionType type) {
        super(message);
        this.exceptionType=type;
    }

    public CSVBuilderException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.exceptionType=type;

    }
}
