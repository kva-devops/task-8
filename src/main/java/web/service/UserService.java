package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User findUserById(long id);

    User updateUser(long id, User user);

    void deleteUserById(long id);

    List<User> getAllUsers();
}
