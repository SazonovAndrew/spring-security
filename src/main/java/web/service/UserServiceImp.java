package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;
   @Autowired
   private RoleDao roleDao;
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   @Override
   public boolean create(User user) {
      Set<Role> roleSet = (Set<Role>) user.getAuthorities();
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      user.setRoles(roleSet);
      return userDao.create(user);
   }

   @Override
   public User getUserById(int id) {
      return userDao.getUserById(id);
   }


   @Override
   public List<User> index() {
      return userDao.index();
   }

   @Override
   public void update(User user) {
      Set<Role> roleSet = (Set<Role>) user.getAuthorities();
      user.setRoles(roleSet);
      if(user.getPassword().equals("")){
         user.setPassword(getUserById(user.getId()).getPassword());
      }else{
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      }
      userDao.update(user);
   }

   @Override
   public void delete(int id) {
      userDao.delete(id);
   }

   @Override
   public User findByUserForUsername(String username) {
      return userDao.findByUserForUsername(username);
   }

   @Override
   public List<Role> allRoles() {
      return roleDao.allRoles();
   }

   @Override
   public Role findById(Long id) {
      return roleDao.findById(id);
   }

   @Override
   public void deleteRole(Long id) {
      roleDao.deleteRole(id);
   }

   @Override
   public boolean saveRole(Role role) {
      return roleDao.saveRole(role);
   }

   @Override
   public boolean userExist(String username) {
      return userDao.userExist(username);
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return findByUserForUsername(username);
   }
   @Override
   public void addRolesToUser(User user, List<String> addingRoles) {
      Set<Role> roleSet = new HashSet<>();
      if(addingRoles.isEmpty()){
         user.setRoles(Collections.singleton(new Role(2L,"ROLE_USER")));
      }
      if (addingRoles.contains("ROLE_ADMIN")){
         roleSet.add(new Role(1L, "ADMIN"));
         user.setRoles(roleSet);
      }
      if (addingRoles.contains("ROLE_USER")) {
         roleSet.add(new Role(2L, "USER"));
         user.setRoles(roleSet);
      }
   }
}

