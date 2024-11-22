package be.study.tennisBe.security.service.impl;

import be.study.tennisBe.model.administrator.UserTennis;
import be.study.tennisBe.service.administrator.UserTennisService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public PasswordEncoder passwordEncoder;
    private final UserTennisService userTennisService;

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Autowired
    public UserDetailsServiceImpl(UserTennisService userTennisService,
                                  PasswordEncoder passwordEncoder) {
        this.userTennisService = userTennisService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println(userTennisService != null ?
                "UserTennisService is injected" : "UserTennisService is null");

        UserTennis userTennis = userTennisService.getUserTennisByUsername(username);
        if(userTennis == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }

        // System.out.println("userScore: " + userScore.getUsername() +
        //         " - " + userScore.getPassword() +
        //         " - " + userScore.getRoles()
        // );

        System.out.println("password encrypted:" +
                passwordEncoder.encode(userTennis.getPassword()));

        //List<Role> roleList = userScore.getRoles();
        //Set<Role> roles = getRoles(roleList);

        return User.withUsername(userTennis.getUsername())
                .password(userTennis.getPassword())
                //.authorities(getAuthorities(roles, userScore))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    /*    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roleSet, UserScore userScore)
        {
            List<Role> roleList = userScore.getRoles();
            Set<Role> roles = getRoles(roleList);

            List<GrantedAuthority> authList = getGrantedAuthorities(roles, userScore);
            return authList;
        }
    */
    public static String getUserId(UserTennis userTennis) {
        String theId = Long.toString(userTennis.getId());
        return theId;
    }
/*
    public Set<Role> getRoles(List<Role> roleList) {
        return new HashSet<>(roleList);
    }

    public static List<GrantedAuthority> getGrantedAuthorities(Set<Role> roles, UserScore userScore) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        authorities.add(new SimpleGrantedAuthority(getUserId(userScore)));

        return authorities;
    }
*/
}

