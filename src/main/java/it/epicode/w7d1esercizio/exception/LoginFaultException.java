package it.epicode.w7d1esercizio.exception;

public class LoginFaultException extends RuntimeException{
    public LoginFaultException(String message){
        super(message);
    }
}
