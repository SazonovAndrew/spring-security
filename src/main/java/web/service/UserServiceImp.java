package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;
   @Autowired
   private RoleDao roleDao;
   @Transactional
   @Override
   public boolean create(User user) {
      return userDao.create(user);
   }
   @Transactional
   @Override
   public User show(int id) {
      return userDao.show(id);
   }
   @Transactional
   @Override
   public List<User> index() {
      return userDao.index();
   }
   @Transactional
   @Override
   public void update(int id, User user) {
      userDao.update(id, user);
   }
   @Transactional
   @Override
   public void delete(int id) {
      userDao.delete(id);
   }
   @Transactional
   @Override
   public User findByUserForUsername(String username) {
      return userDao.findByUserForUsername(username);
   }
   @Transactional
   @Override
   public List<Role> allRoles() {
      return roleDao.allRoles();
   }
   @Transactional
   @Override
   public Role findById(Long id) {
      return roleDao.findById(id);
   }
   @Transactional
   @Override
   public void deleteRole(Long id) {
      roleDao.deleteRole(id);
   }
   @Transactional
   @Override
   public boolean saveRole(Role role) {
      return roleDao.saveRole(role);
   }
   @Transactional
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return findByUserForUsername(username);
   }
}

