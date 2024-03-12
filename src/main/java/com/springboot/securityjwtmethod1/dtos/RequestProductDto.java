package com.springboot.securityjwtmethod1.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class RequestProductDto {

    @NotBlank
    private String productCode;

    @NotBlank
    private String name;

    @NotBlank
    private String productSection;

    @Positive
    private Double price;

    @NotBlank
    private String country;

}
