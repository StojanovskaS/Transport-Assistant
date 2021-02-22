package com.projectweb.transportassistant.service.implementation;

import com.projectweb.transportassistant.model.SiteUser;
import com.projectweb.transportassistant.model.enumerations.Role;
import com.projectweb.transportassistant.model.exeptions.InvalidUsercCredentialException;
import com.projectweb.transportassistant.model.exeptions.PasswordsdoNotMatchException;
import com.projectweb.transportassistant.model.exeptions.UserNotFoundExeption;
import com.projectweb.transportassistant.model.exeptions.UsernameAlreadyExistsException;
import com.projectweb.transportassistant.repository.SiteUserRepository;
import com.projectweb.transportassistant.service.SiteUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.InvalidModuleDescriptorException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SiteUserServiceImpl implements SiteUserService, UserDetailsService {
    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUserServiceImpl(SiteUserRepository siteUserRepository, PasswordEncoder passwordEncoder) {
        this.siteUserRepository = siteUserRepository;
        this.passwordEncoder=passwordEncoder;

    }



    @Override
    public SiteUser login(String username, String password) {
        return this.siteUserRepository.findByUsernameAndPassword(username,passwordEncoder.encode(password)).orElseThrow(InvalidUsercCredentialException::new);
    }

    @Override
    public SiteUser register(String username, String email, String firstname, String lastname, String password, String repeatpassword, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsercCredentialException();
        if (firstname==null || firstname.isEmpty() || lastname==null || lastname.isEmpty()){
            throw new InvalidUsercCredentialException();
        }
        if (!password.equals(repeatpassword))
            throw new PasswordsdoNotMatchException();
        if(this.siteUserRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        String newpass=passwordEncoder.encode(password);
        SiteUser user=new SiteUser(username,email,firstname,lastname,newpass,role);
        return siteUserRepository.save(user);
    }

    @Override
    public SiteUser findByUserName(String username) {
        return this.siteUserRepository.findByUsername(username).orElseThrow(UserNotFoundExeption::new);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SiteUser user=siteUserRepository.findByUsername(s).orElseThrow(InvalidModuleDescriptorException::new);
        UserDetails user1 =new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));
        return user1;
    }
}
