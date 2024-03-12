package com.springboot.securityjwtmethod1.dtos;

import com.springboot.securityjwtmethod1.enums.RoleName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestRoleDto {

    @NotNull
    private RoleName roleName;

}
