package com.springboot.securityjwtmethod1.services;

import com.springboot.securityjwtmethod1.exceptions.RoleNotFoundException;
import com.springboot.securityjwtmethod1.exceptions.UserNotFoundException;
import com.springboot.securityjwtmethod1.exceptions.UsernameExistsException;
import com.springboot.securityjwtmethod1.models.Role;
import com.springboot.securityjwtmethod1.models.User;
import com.springboot.securityjwtmethod1.repositories.RoleRepository;
import com.springboot.securityjwtmethod1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User register(User user,List<Long> roleIds) throws UsernameExistsException, RoleNotFoundException {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UsernameExistsException("Username: "+user.getUsername()+" already exists!");
        }
        List<Role> roles= new ArrayList<>();
        for(Long roleId: roleIds){
            Role role= roleRepository.findById(roleId)
                    .orElseThrow(()-> new RoleNotFoundException("Role with id: "+roleId+" not found!"));
            roles.add(role);
        }
        user.getRoles().addAll(roles);
        //roles.forEach(role->role.getUsers().add(user));//can be replaced with with cascade = CascadeType.PERSIST over "List<Role> roles= new ArrayList<>();" relation in User
        return userRepository.save(user);
    }

    @Transactional
    public User delete(Long id) throws UserNotFoundException {
        User user= userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with id: "+id+" not found!"));

        List<Role> roles= user.getRoles();
        roles.forEach(role-> role.getUsers().remove(user));

        userRepository.delete(user);
        return user;
    }

}
