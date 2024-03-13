package com.springboot.securityjwtmethod1.controllers;


import com.springboot.securityjwtmethod1.exceptions.RoleNotFoundException;
import com.springboot.securityjwtmethod1.models.Role;
import com.springboot.securityjwtmethod1.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Page<Role>> getRoles(@PageableDefault(size = 5) Pageable pageable){
        Page<Role> rolesPage= roleService.getRoles(pageable);
        return ResponseEntity.ok(rolesPage);
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable Long id){
        try {
            Role role= roleService.getRoleById(id);
            return new ResponseEntity<>(role,HttpStatus.OK);
        } catch (RoleNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




}
