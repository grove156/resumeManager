package com.projects.resumeManager.exceptionHandle.exceptions;

import lombok.Getter;

@Getter
public class ResumeListNoExistException extends RuntimeException {

    public ResumeListNoExistException(String message){
        super(message);
    }

    public ResumeListNoExistException(String message, Throwable throwable){
        super(message, throwable);
    }
}
