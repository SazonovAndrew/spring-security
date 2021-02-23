package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {

   User show(int id);
   List<User> index();
   boolean create(User user);
   void update(User user);
   void delete(int id);
   User findByUserForUsername(String username);

}
