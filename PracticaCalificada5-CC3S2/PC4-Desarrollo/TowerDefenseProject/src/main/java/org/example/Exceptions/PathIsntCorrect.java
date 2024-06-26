package org.example.Exceptions;

public class PathIsntCorrect extends RuntimeException{
    public PathIsntCorrect(String message){
        super(message);
    }
}
