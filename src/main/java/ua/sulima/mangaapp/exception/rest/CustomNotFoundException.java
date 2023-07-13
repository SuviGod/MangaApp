package ua.sulima.mangaapp.exception.rest;

public class CustomNotFoundException extends RuntimeException{
    public CustomNotFoundException(String message){
        super(message);
    }
}
