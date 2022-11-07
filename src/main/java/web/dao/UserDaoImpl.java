package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void createUser(User user) {
        var entityManager = entityManagerFactory.createEntityManager();
        var transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> findUsersById(long id) {
        List<User> userList = new ArrayList<>();
        userList = entityManagerFactory
                .createEntityManager()
                .createQuery(
                        "SELECT u FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .getResultList();
        return userList;
    }

    @Override
    public User updateUser(User user) {
        var entityManager = entityManagerFactory.createEntityManager();
        var transaction = entityManager.getTransaction();
        User result = null;
        try {
            transaction.begin();
            result = entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        return result;
    }

    @Override
    public void deleteUserById(long id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createQuery("DELETE FROM User u WHERE u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return entityManagerFactory
                .createEntityManager()
                .createQuery(
                        "SELECT u FROM User u ORDER BY u.id")
                .getResultList();
    }
}
