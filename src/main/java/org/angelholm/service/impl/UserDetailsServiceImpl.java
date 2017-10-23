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

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.getUser(login);
        System.out.println("User : "+ user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getEnabled(), true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        /*
        for(UserProfile userProfile : user.getUserProfiles()){
            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        System.out.print("authorities :"+authorities);
        */
        RoleDaoImpl roleDao = new RoleDaoImpl();
        List<Role> allRoles = null;
        try {
            allRoles = roleDao.getAllRoles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allRoles.size(); i++) {
            Role role = allRoles.get(i);
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }


        return authorities;
    }



}
