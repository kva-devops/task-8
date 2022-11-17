package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);

    User findUserById(long id);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> getAllUsers();
}
