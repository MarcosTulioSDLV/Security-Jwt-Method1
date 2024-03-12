package com.springboot.securityjwtmethod1.dtos;

import com.springboot.securityjwtmethod1.enums.RoleName;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestLoginDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
