package com.springboot.securityjwtmethod1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RequestProductDto (@NotBlank String productCode,
                                 @NotBlank String name,
                                 @NotBlank String productSection,
                                 @Positive Double price,
                                 @NotBlank String country){

}