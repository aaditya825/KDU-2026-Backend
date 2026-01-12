package com.logisticshub.demo.exception;
public class ErrorResponse
{
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse()
    {}

    public ErrorResponse(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
