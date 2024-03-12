package com.springboot.securityjwtmethod1.controllers;

import com.springboot.securityjwtmethod1.dtos.RequestLoginDto;
import com.springboot.securityjwtmethod1.dtos.RequestRegisterDto;
import com.springboot.securityjwtmethod1.exceptions.RoleNotFoundException;
import com.springboot.securityjwtmethod1.exceptions.UserNotFoundException;
import com.springboot.securityjwtmethod1.exceptions.UsernameExistsException;
import com.springboot.securityjwtmethod1.models.User;
import com.springboot.securityjwtmethod1.security.JwtService;
import com.springboot.securityjwtmethod1.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RequestRegisterDto requestRegisterDto){
        User user= new User();
        BeanUtils.copyProperties(requestRegisterDto,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Long> roleIds= requestRegisterDto.getRoleIds();
        try {
            User addedUser= authenticationService.register(user,roleIds);
            return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
        } catch (UsernameExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (RoleNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody @Valid RequestLoginDto requestLoginDto){
        User user= new User();
        BeanUtils.copyProperties(requestLoginDto,user);

        var authentication= new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        var auth= authenticationManager.authenticate(authentication);

        String token= jwtService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            User deletedUser= authenticationService.delete(id);
            return new ResponseEntity<>(deletedUser,HttpStatus.OK);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
