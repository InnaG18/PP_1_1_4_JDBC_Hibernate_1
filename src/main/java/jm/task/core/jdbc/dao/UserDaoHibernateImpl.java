package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction = null;
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(" +
                    "id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age BIGINT, PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                User user = new User(name, lastName, age);
                session.save(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> results = new ArrayList<>();

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            results =  session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE table users").executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
