package be.study.tennisBe.repository.administrator;

import be.study.tennisBe.model.administrator.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(final String name);
}
