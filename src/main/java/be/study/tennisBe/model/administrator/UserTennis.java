package be.study.tennisBe.model.administrator;

import be.study.tennisBe.model.dto.UserTennisDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tennis")
public class UserTennis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    private String username;
    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 64)
    private String oldPassword;
    @Column(nullable = false)
    private boolean isActive;

    @ManyToMany
    @JoinTable(name = "users_roles")
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public static UserTennis from(UserTennisDto userTennisDto) {
        UserTennis usertennis = new UserTennis();
        usertennis.setEmail(userTennisDto.getEmail());
        usertennis.setPassword(userTennisDto.getPassword());
        usertennis.setUsername(userTennisDto.getUsername());
        usertennis.setOldPassword(userTennisDto.getOldPassword());
        usertennis.setActive(userTennisDto.isActive());
        usertennis.setRoles(userTennisDto.getRolesDto()
                .stream().map(Role::from).collect(Collectors.toList()));
        return usertennis;
    }
}
