package com.springboot.securityjwtmethod1.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(){
    }

    public ProductNotFoundException(String message){
        super(message);
    }

}
