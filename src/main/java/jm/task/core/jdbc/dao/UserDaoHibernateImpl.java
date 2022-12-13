package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users" +
                    " (id int AUTO_INCREMENT, first_name VARCHAR(50), " +
                    "last_name VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Не удалось создать таблицу");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

        @Override
        public void dropUsersTable () {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Не удалось удалить таблицу");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        @Override
        public void saveUser (String name, String last_name,byte age){
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                User user = new User(name, last_name, age);
                session.save(user);
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Не удалось сохранить пользователя");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        @Override
        public void removeUserById ( long id){
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                User user = (User) session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Не удалось удалить пользователя");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

        @Override
        public List<User> getAllUsers () {
            Transaction transaction = null;
            List<User> result = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
                criteriaQuery.from(User.class);
                result = session.createQuery(criteriaQuery).getResultList();
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Не удалось получить список пользователей");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
            return result;
        }

        @Override
        public void cleanUsersTable () {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.createNativeQuery("TRUNCATE TABLE users_db2.users;").executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                System.out.println("Не удалось очистить таблицу");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
