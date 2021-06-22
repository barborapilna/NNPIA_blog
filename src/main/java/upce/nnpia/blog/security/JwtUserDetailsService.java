package upce.nnpia.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upce.nnpia.blog.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao user;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        var found = user.findByUsername(username);
        return new UserDetail(found.getUsername(), found.getPassword(), new ArrayList<>());
    }
}