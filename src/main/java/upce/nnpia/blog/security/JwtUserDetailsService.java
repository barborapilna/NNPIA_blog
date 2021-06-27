package upce.nnpia.blog.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.UserDao;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserDao user;

    public JwtUserDetailsService(UserDao user) {
        this.user = user;
    }

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        var found = user.findByUsername(username);
        return new UserDetail(found.getUsername(), found.getPassword(), List.of(new SimpleGrantedAuthority(found.getRole().getRoleName().name())));
    }
}