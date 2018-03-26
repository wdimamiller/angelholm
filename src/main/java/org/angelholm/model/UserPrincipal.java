package org.angelholm.model;

import org.hl7.fhir.dstu3.model.Practitioner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class UserPrincipal extends User{

    private Practitioner practitioner;

    public UserPrincipal( Practitioner practitioner,
                          String username,
                          String password,
                          boolean enabled,
                          boolean accountNonExpired,
                          boolean credentialsNonExpired,
                          boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities){
        super( username,  password,  enabled,  accountNonExpired,  credentialsNonExpired,  accountNonLocked,  authorities);
        this.practitioner = practitioner;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

}
