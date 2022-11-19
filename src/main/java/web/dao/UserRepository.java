package web.dao;

import org.springframework.data.repository.CrudRepository;
import web.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAllByOrderById();
}
