package org.angelholm.service.impl;

import org.angelholm.dao.RoleDaoImpl;
import org.angelholm.model.Role;
import org.angelholm.model.User;
import org.angelholm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.getUser(login);
        System.out.println("Користувач : "+ user);
        if(user==null){
            System.out.println("Користувача не знайдено");
            throw new UsernameNotFoundException("Такого логіну не існує");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getEnabled(), true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Set<Role> roles = user.getRoles();

        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }



}
