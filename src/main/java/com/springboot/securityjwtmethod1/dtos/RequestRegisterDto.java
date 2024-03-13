package com.springboot.securityjwtmethod1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.List;

public record RequestRegisterDto(@NotBlank String username,
                                 @NotBlank String password,
                                 @NotNull List<Long> roleIds) {

}

