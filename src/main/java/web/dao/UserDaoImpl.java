package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public boolean checkUser(User user) {
        Query query = entityManager.createQuery("select a from User a where a.name=:name")
                .setParameter("name", user.getName());
        return query.getResultList().isEmpty();
    }

    @Override
    public void updateUser(User user) {
        Query query = entityManager.createQuery("update User set age=:age, street=:street where id=:id");
        query.setParameter("age", user.getAge());
        query.setParameter("street", user.getStreet());
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = entityManager.createQuery("select s from User s");
        return query.getResultList();
    }

    @Override
    public User findUserByName(String name) {
        Query query = entityManager.createQuery("select us, role from Role role inner join User us on us.id=role.id where us.name=:name")
                .setParameter("name", name);
        Object[] obj = (Object[]) query.getResultList().get(0);
        User us = (User) obj[0];
        Set<Role> role = new HashSet<>();
        role.add((Role) obj[1]);
        return new User(us.getId(), us.getName(), us.getAge(), us.getStreet(), us.getPassword(), role);
    }

    @Override
    public void deleteUser(Long id) {
        Query query = entityManager.createQuery("DELETE from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
