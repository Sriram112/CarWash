package com.casestudy.springsecurity.service;

import com.casestudy.springsecurity.models.Roles;
import com.casestudy.springsecurity.models.Users;
import com.casestudy.springsecurity.repository.RoleRepository;
import com.casestudy.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    // @Autowired
    //private AuthService as;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    // to find user by mailId
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //to update the token of the user everytime the user logins
    public Users updateTokenById(Users ExistingUser, String token) {
        ExistingUser.setToken(token);
        return userRepository.save(ExistingUser);
    }

    //After registering save user by his role
    public Users saveUser(Users user) {
        //Bcrypt is a one way strong Hashing function
        //calling a bean in configuration file(WebSecurityConfig)
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        System.out.println(user.getRoles());
        List<Roles> admin = user.getRoles().stream().filter(x -> x.getRole().contains("ADMIN")).collect(Collectors.toList());
        List<Roles> washer = user.getRoles().stream().filter(x -> x.getRole().contains("WASHER")).collect(Collectors.toList());
        if (washer.size() == 1) {
            Roles userRole = roleRepository.findByRole("WASHER");
            user.setRoles((new HashSet<>(Arrays.asList(userRole))));
        } else if (admin.size() == 1) {
            Roles userRole = roleRepository.findByRole("ADMIN");
            user.setRoles((new HashSet<>(Arrays.asList(userRole))));
        } else {
            Roles userRole = roleRepository.findByRole("USER");
            user.setRoles((new HashSet<>(Arrays.asList(userRole))));
        }
        //logging the saved user in console
        System.out.println(user);
        return userRepository.save(user);
    }


    //@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if(user!=null){

            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return builderUserForAuthentication(user, authorities);
        }
        else{
            throw new UsernameNotFoundException("username not found in database");
        }
    }
    public List<GrantedAuthority> getUserAuthority(Set<Roles> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role)->{
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }
    public UserDetails builderUserForAuthentication(Users user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
