package ua.sulima.mangaapp.exception.rest;

public class CustomFileNotFoundException extends RuntimeException{

    public CustomFileNotFoundException(String message){
        super(message);
    }
}
