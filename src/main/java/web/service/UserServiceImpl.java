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
    public User findUserById(long id) {
        List<User> userList = userDao.findUsersById(id);
        return userList.get(0);
    }

    @Transactional
    @Override
    public User updateUser(long id, User user) {
        List<User> userListFromDB = userDao.findUsersById(id);
        if (user.getFirstName().equals("")) {
            user.setFirstName(userListFromDB.get(0).getFirstName());
        }
        if (user.getLastName().equals("")) {
            user.setLastName(userListFromDB.get(0).getLastName());
        }
        user.setMarried(user.isMarried());
        user.setId(id);
        return userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
