package web.dao;

import web.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserDao {

   User show(int id);
   List<User> index();
   boolean create(User user);
   void update(int id, User user);
   void delete(int id);
   User findByUserForUsername(String username);

//   void deleteUser(int id);
//   User updateUser(User user);
//   User getUsersWithCar(String name, int series);
}
