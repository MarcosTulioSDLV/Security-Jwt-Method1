package com.springboot.securityjwtmethod1.services;

import com.springboot.securityjwtmethod1.exceptions.RoleNameExistsException;
import com.springboot.securityjwtmethod1.exceptions.RoleNotFoundException;
import com.springboot.securityjwtmethod1.exceptions.UsernameExistsException;
import com.springboot.securityjwtmethod1.models.Role;
import com.springboot.securityjwtmethod1.models.User;
import com.springboot.securityjwtmethod1.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Page<Role> getRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Role getRoleById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(()-> new RoleNotFoundException("Role with id: "+id+" not found!"));
    }

    @Transactional
    public Role addRole(Role role) throws RoleNameExistsException {
        if(roleRepository.existsByRoleName(role.getRoleName())){
            throw new RoleNameExistsException("Role name: "+role.getRoleName()+" already exists!");
        }
        return roleRepository.save(role);
    }



}
