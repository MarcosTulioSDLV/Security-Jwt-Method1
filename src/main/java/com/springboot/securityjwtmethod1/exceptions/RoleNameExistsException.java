package com.springboot.securityjwtmethod1.exceptions;

public class RoleNameExistsException extends Exception{

    public RoleNameExistsException(){
    }

    public RoleNameExistsException(String message){
        super(message);
    }

}
