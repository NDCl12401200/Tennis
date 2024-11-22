package be.study.tennisBe.service.administrator;

import be.study.tennisBe.exceptions.IdNotFoundException;
import be.study.tennisBe.model.administrator.Role;
import be.study.tennisBe.repository.administrator.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getRoles() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Role   getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "Role"));
    }

    public Role deletRole(Long id) {
        Role role = getRoleById(id);
        roleRepository.delete(role);
        return role;
    }

    public Role editRole(Long id, Role role) {
        Role roleToEdit = getRoleById(id);
        roleToEdit.setName(role.getName());
        roleRepository.save(roleToEdit);
        return roleToEdit;
    }
}