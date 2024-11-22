package be.study.tennisBe.controller.administrator;

import be.study.tennisBe.model.administrator.Role;
import be.study.tennisBe.model.dto.RoleDto;
import be.study.tennisBe.service.administrator.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    private final RoleService roleService;
    
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getRoles() {
        List<Role> roles = roleService.getRoles();
        List<RoleDto> roleDto = roles.stream().map(RoleDto::from).toList();
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }
}
