package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserById(int id);
    List<User> index();
    boolean create(User user);
    void update(User user);
    void delete(int id);
    User findByUserForUsername(String username);
    List<Role> allRoles();
    Role findById(Long id);
    void deleteRole(Long id);
    boolean saveRole(Role role);
    boolean userExist(String username);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
