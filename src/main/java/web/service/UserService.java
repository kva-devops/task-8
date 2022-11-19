package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Integer id);

    void deleteUserById(Integer id);

    List<User> getAllUsers();

    void createOrUpdateUser(User user);
}
