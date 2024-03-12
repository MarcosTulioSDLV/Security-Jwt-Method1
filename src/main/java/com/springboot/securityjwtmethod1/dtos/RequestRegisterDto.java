package com.springboot.securityjwtmethod1.dtos;

import com.springboot.securityjwtmethod1.enums.RoleName;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RequestRegisterDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private List<Long> roleIds;

}
