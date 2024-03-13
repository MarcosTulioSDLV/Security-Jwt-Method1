package com.springboot.securityjwtmethod1.dtos;

import jakarta.validation.constraints.NotBlank;


public record RequestLoginDto(@NotBlank String username,
                              @NotBlank String password) {

}
