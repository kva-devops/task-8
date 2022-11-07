package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User findUserById(String id);

    User updateUser(String id, User user);

    void deleteUserById(String id);

    List<User> getAllUsers();
}
