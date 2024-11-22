package be.study.tennisBe.service.administrator;

import be.study.tennisBe.exceptions.IdNotFoundException;
import be.study.tennisBe.exceptions.UserTennisNotFoundException;
import be.study.tennisBe.model.administrator.UserTennis;
import be.study.tennisBe.repository.administrator.UserTennisRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserTennisService {

    private final UserTennisRepository userTennisRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Autowired
    public UserTennisService(UserTennisRepository userTennisRepository, PasswordEncoder passwordEncoder,
            RoleService roleService) {
        System.out.println("Mon password: " + passwordEncoder.encode("123"));
        this.userTennisRepository = userTennisRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public UserTennis addUserTennis(UserTennis userTennis) {
        String encodedPassword = passwordEncoder.encode(userTennis.getPassword());
        userTennis.setPassword(encodedPassword);
        return userTennisRepository.save(userTennis);
    }

    public List<UserTennis> getUsersTennis() {
        return StreamSupport.stream(userTennisRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public UserTennis getUserTennisById(Long id) {
        return userTennisRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "UserTennis"));
    }

    public UserTennis getUserTennisByUserName(String username) {
    //     System.out.println(
    //             userTennisRepository != null ? "UserTennisRepository is injected" : "UserTennisRepository is null");

        UserTennis userTennis = userTennisRepository.findByUsername(username);
        if (userTennis == null) {
            throw new UserTennisNotFoundException(username);
        }
        return userTennis;
    }

    public UserTennis getUserTennisByEmail(String email) {
        System.out.println(
                userTennisRepository != null ? "UserTennisRepository is injected" : "UserTennisRepository is null");

        UserTennis userTennis = userTennisRepository.findByEmail(email);
        if (userTennis == null) {
            throw new UserTennisNotFoundException(email);
        }
        return userTennis;
    }

    public UserTennis deleteUserTennis(Long id) {
        UserTennis userTennis = getUserTennisById(id);
        userTennisRepository.delete(userTennis);
        return userTennis;
    }

    public UserTennis editUserTennis(Long id, UserTennis userTennis) {
        UserTennis userTennisToEdit = getUserTennisById(id);
        userTennisToEdit.setEmail(userTennis.getEmail());
        userTennisToEdit.setUsername(userTennis.getUsername());
        userTennisToEdit.setActive(userTennis.isActive());
        userTennisRepository.save(userTennisToEdit);
        return userTennisToEdit;
    }

    public UserTennis getUserTennisByUsername(String username) {
        return userTennisRepository.findByUsername(username);
    }

    @Transactional
    public UserTennis addRoleToUserTennis(Long idUserTennis, Long idRole) {
        UserTennis userTennis = getUserTennisById(idUserTennis);
        userTennis.addRole(roleService.getRoleById(idRole));
        return userTennis;
    }

    @Transactional
    public UserTennis removeRoleFromUserTennis(Long idUserTennis, Long idRole) {
        UserTennis userTennis = getUserTennisById(idUserTennis);
        userTennis.getRoles().remove(roleService.getRoleById(idRole));
        return userTennis;
    }
}
