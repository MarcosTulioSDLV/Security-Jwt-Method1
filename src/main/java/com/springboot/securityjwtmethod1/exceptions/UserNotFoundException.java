package com.springboot.securityjwtmethod1.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
    }
    public UserNotFoundException(String message){
        super(message);
    }

}
