package com.springboot.securityjwtmethod1.exceptions;

public class RoleNotFoundException extends Exception{

    public RoleNotFoundException(){
    }

    public RoleNotFoundException(String message){
        super(message);
    }

}
