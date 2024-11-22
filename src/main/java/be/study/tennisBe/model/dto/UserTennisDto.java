

package be.study.tennisBe.model.dto;

import be.study.tennisBe.model.administrator.UserTennis;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserTennisDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String oldPassword;
    private boolean isActive;
    private List<RoleDto> rolesDto = new ArrayList<>();

    public static UserTennisDto from(UserTennis userTennis) {
        UserTennisDto userTennisDto = new UserTennisDto();
        userTennisDto.setId(userTennis.getId());
        userTennisDto.setUsername(userTennis.getUsername());
        userTennisDto.setEmail(userTennis.getEmail());
        userTennisDto.setPassword(userTennis.getPassword());
        userTennisDto.setOldPassword(userTennis.getOldPassword());
        userTennisDto.setActive(userTennis.isActive());
        userTennisDto.setRolesDto(userTennis.getRoles()
                .stream().map(RoleDto::from).collect(Collectors.toList()));
        return userTennisDto;
    }
}
