package com.springboot.securityjwtmethod1.exceptions;

public class UsernameExistsException extends Exception{

    public UsernameExistsException(){
    }

    public UsernameExistsException(String message){
        super(message);
    }

}
