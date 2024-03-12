package com.springboot.securityjwtmethod1.exceptions;

public class ProductCodeExistsException extends Exception{

    public ProductCodeExistsException(){
    }

    public ProductCodeExistsException(String message){
        super(message);
    }

}
