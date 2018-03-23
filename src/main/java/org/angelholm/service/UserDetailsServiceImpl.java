package org.angelholm.service;

import org.angelholm.model.UserPrincipal;
import org.hl7.fhir.dstu3.model.Practitioner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService{


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        PractitionerService practitionerService = new PractitionerService();


        Practitioner practitioner = practitionerService.getPractitionerByIdentifier();

        System.out.println("Користувач : "+ practitioner.getName().get(0).getText());
        if(practitioner==null){
            System.out.println("Користувача не знайдено");
            throw new UsernameNotFoundException("Такого логіну не існує");
        }
        System.out.println(
                "I AM IN USER DETAILS SERVICE"
        );

        UserPrincipal userPrincipal = new UserPrincipal(practitioner,practitioner.getIdentifier().get(0).getValue(),
                "admin",
                true,
                true,
                true,
                true,
                getGrantedAuthorities());

        return userPrincipal;

    }

    private List<GrantedAuthority> getGrantedAuthorities(){

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String roleCode = "admin";

        switch (roleCode){
            case "admin":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case "doctor":
                authorities.add(new SimpleGrantedAuthority("ROLE_DOCTOR"));
                break;
            case "manager":
                authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                break;
            case "nurce":
                authorities.add(new SimpleGrantedAuthority("ROLE_NURCE"));
                break;
            case "lab_assistant":
                authorities.add(new SimpleGrantedAuthority("ROLE_LAB_ASSISTANT"));
                break;
            case "accountant":
                authorities.add(new SimpleGrantedAuthority("ROLE_ACCOUNTANT"));
                break;
            case "resperson":
                authorities.add(new SimpleGrantedAuthority("ROLE_RES_PERSON"));
                break;
        }

        return authorities;
    }
}
