package survey.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import survey.backend.entities.User;
import survey.backend.entities.User_Role;
import survey.backend.repository.UserRepository;
import survey.backend.vo.Request;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).get();

        List<User_Role> userRoles = user.getUserRoles().stream().collect(Collectors.toList());

        List<GrantedAuthority> grantedAuthorities = userRoles.stream().map(r -> {
            return new SimpleGrantedAuthority(r.getRole());
        }).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);
    }

    public void saveUser(Request request) {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setUserPass(passwordEncoder.encode(request.getUserPwd()));

        user.setUserRoles(request.getRoles().stream().map(r -> {
            User_Role ur = new User_Role();
            ur.setRole(r);
            return ur;
        }).collect(Collectors.toSet()));

        userRepository.save(user);
    }

}
