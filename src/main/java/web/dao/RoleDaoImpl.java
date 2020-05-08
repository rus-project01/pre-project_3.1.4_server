package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    public Role findByName(String role) {
        Query query = entityManager.createQuery("select role from Role role where role.name=:name");
        query.setParameter("name", role);
        return (Role) query.getSingleResult();
    }

    public Role findRoleByNameUs(String role) {
        Query query = entityManager.createQuery("select us, roles from Role roles inner join User us on us.id=roles.id where us.name=:name")
                .setParameter("name", role);
        Object[] obj = (Object[]) query.getResultList().get(0);
        User us = (User) obj[0];
        return new Role(us.getRole().get(0).getId(), us.getRole().get(0).getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listRoles() {
        Query query = entityManager.createQuery("select s from Role s");
        return query.getResultList();
    }
}
