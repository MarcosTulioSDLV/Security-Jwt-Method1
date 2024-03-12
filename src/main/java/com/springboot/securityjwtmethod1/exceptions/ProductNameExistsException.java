package com.springboot.securityjwtmethod1.exceptions;

public class ProductNameExistsException extends Exception{

    public ProductNameExistsException(){
    }

    public ProductNameExistsException(String message){
        super(message);
    }

}
