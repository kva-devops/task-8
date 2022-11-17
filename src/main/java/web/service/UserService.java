package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    User findUserById(long id);

    void deleteUserById(long id);

    List<User> getAllUsers();

    void createOrUpdateUser(User user);
}
