package org.goaler.springsecuritydemo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

//@Component//因为用了inMemoryAuthentication，这里注释掉
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String pwd = (String) authentication.getCredentials();


        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(name);

        if (userDetails == null || !pwd.equals(userDetails.getPassword())){
            throw new BadCredentialsException("认证失败");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, pwd, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
