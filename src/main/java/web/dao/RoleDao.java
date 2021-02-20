package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> allRoles();

    Role findById(Long id);

    void deleteRole(Long id);

    boolean saveRole(Role role);

}
