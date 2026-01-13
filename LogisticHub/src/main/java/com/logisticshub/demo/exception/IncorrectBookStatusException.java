package com.logisticshub.demo.exception;

public class IncorrectBookStatusException extends RuntimeException
{
    public IncorrectBookStatusException(String message)
    {
        super(message);
    }
}
