package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    public EntityManager entityManager;

    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        Query query = entityManager.createQuery("update Role set name=:name where id=:id");
        query.setParameter("name", role.getName());
        query.setParameter("id", role.getId());
        query.executeUpdate();
    }
}
