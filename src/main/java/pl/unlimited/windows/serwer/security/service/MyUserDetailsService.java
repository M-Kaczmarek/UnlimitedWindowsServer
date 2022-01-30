package pl.unlimited.windows.serwer.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.model.Role;
import pl.unlimited.windows.serwer.model.SystemUser;
import pl.unlimited.windows.serwer.repository.SystemUserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SystemUser candidate = systemUserRepository.findSystemUserByLogin(s);
        if(candidate == null){
            return  null;
        }
        return new User(candidate.getLogin(), candidate.getPassword(), new ArrayList<>());
    }

    public SystemUser saveCandidate(SystemUser candidate){
        candidate.setRole(Role.USER);
        candidate.setPassword(bCryptPasswordEncoder.encode(candidate.getPassword()));
        return systemUserRepository.save(candidate);
    }
}