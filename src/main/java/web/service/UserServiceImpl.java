package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    @Override
    public User findUserById(String id) {
        List<User> userList = userDao.findUsersById(Integer.parseInt(id));
        try {
            if (userList.isEmpty()) {
                throw new NullPointerException("User not found");
            }
        } catch (RuntimeException e) {
            return null;
        }
        return userList.get(0);
    }

    @Transactional
    @Override
    public User updateUser(String id, User user) {
        List<User> userListFromDB = userDao.findUsersById(Integer.parseInt(id));
        if (userListFromDB.isEmpty()) {
            throw new NullPointerException("User not found");
        }
        if (user.getFirstName().equals("")) {
            user.setFirstName(userListFromDB.get(0).getFirstName());
        }
        if (user.getLastName().equals("")) {
            user.setLastName(userListFromDB.get(0).getLastName());
        }
        user.setMarried(user.isMarried());
        user.setId((long) Integer.parseInt(id));

        User updatedUser = userDao.updateUser(user);
        if (updatedUser == null) {
            throw new RuntimeException();
        }
        return updatedUser;
    }

    @Transactional
    @Override
    public void deleteUserById(String id) {
        userDao.deleteUserById(Integer.parseInt(id));
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        if (userList == null) {
            throw new RuntimeException();
        }
        return userList;
    }
}