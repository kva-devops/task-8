package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserRepository;
import web.model.User;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByOrderById();
    }

    @Transactional
    @Override
    public void createOrUpdateUser(User user) {
        if (user.getId() == null) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private void createUser(User user) {
        userRepository.save(user);
    }

    private void updateUser(User user) {
        Optional<User> userFromDB = userRepository.findById(user.getId());
        if (user.getFirstName().equals("")) {
            user.setFirstName(userFromDB.get().getFirstName());
        }
        if (user.getLastName().equals("")) {
            user.setLastName(userFromDB.get().getLastName());
        }
        user.setMarried(user.isMarried());
        userRepository.save(user);
    }
}
