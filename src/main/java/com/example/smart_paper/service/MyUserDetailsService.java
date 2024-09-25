package com.example.smart_paper.service;
import com.example.smart_paper.models.UserModel;
import com.example.smart_paper.models.UserPrincipal;
import com.example.smart_paper.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String eamil) throws UsernameNotFoundException {
        UserModel user=repo.findByEmail(eamil);
        if(user==null){
            throw new UsernameNotFoundException(eamil);
        }
        return new UserPrincipal(user);
    }

}
