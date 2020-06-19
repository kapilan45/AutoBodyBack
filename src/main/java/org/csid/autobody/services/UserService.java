package org.csid.autobody.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.csid.autobody.controller.DtoConverter;
import org.csid.autobody.dto.UserDto;
import org.csid.autobody.entity.RoleEntity;
import org.csid.autobody.entity.UserEntity;
import org.csid.autobody.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    public static final String SECRET = "SecretKeyToGenJWTs";

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private final RoleService roleService;

    public UserService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserEntity findByUsername(String username){
        UserEntity user = null;
        try{
            user = userRepository.findByUsername(username);
        } catch (Exception e){
            throw e;
        }
        return user;
    }

    public UserEntity getUserByToken(String userToken) {
        String username = getUserNameWithToken(userToken);

        return findByUsername(username);
    }



    public void changePassword(String oldPassword, String newPassword){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity currentUser = findByUsername(currentPrincipalName);

        System.out.println(currentUser.getPassword());
        System.out.println(oldPassword);

        if(passwordEncoder.matches(oldPassword,currentUser.getPassword())){
            userRepository.changePassword(passwordEncoder.encode(newPassword),currentUser.getId());
        }else
            System.out.println("Password doesn't match");
    }



    public List<String> findAllUsernames(){
        List<String> usernamesList= new ArrayList<>();

        try{
            usernamesList = userRepository.findAllUsername();
        } catch (Exception e){
            throw e;
        }

        return usernamesList;
    }

    public void deleteUser(String username){

        UserEntity deletingUser = findByUsername(username);
        Long deletingUserId = deletingUser.getId();
        userRepository.deleteUser(deletingUserId);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }


    public UserDto getCurrentUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        UserEntity u = userRepository.findByUsername(username);

        return DtoConverter.map(u,UserDto.class);
    }

    public UserDto save(UserDto userDto) {

        UserEntity userEntity = DtoConverter.map(userDto, UserEntity.class);

        userEntity.setPassword(getPasswordEncoder().encode(userEntity.getPassword()));

        RoleEntity role = roleService.findByRoleName("ROLE_USER");
        if (role == null) {
        	// TODO create missing from empty db ?!!
        	role = new RoleEntity();
        	role.setRole("ROLE_USER");
        	role = roleService.getRoleRepository().save(role);
        }
        
        if(userEntity.getRole() == null) {
            userEntity.setRole(role); // TODO change to use list ...
        }
        userEntity = this.userRepository.save(userEntity);
        
        UserDto res = DtoConverter.map(userEntity, UserDto.class);
        return res;
    }

    private String getUserNameWithToken(String token){
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

}
