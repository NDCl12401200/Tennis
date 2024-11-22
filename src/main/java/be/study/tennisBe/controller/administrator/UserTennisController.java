package be.study.tennisBe.controller.administrator;

import be.study.tennisBe.exceptions.UserTennisNotFoundException;
import be.study.tennisBe.model.administrator.UserTennis;
import be.study.tennisBe.model.dto.UserTennisDto;
import be.study.tennisBe.service.administrator.UserTennisService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class UserTennisController {

    private final UserTennisService userTennisService;

    @Autowired
    public UserTennisController(UserTennisService userTennisService) {
        this.userTennisService = userTennisService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserTennisDto> getUserTennisById(Long id) {
        UserTennis userTennis = userTennisService.getUserTennisById(id);
        if (userTennis == null)
            throw new UserTennisNotFoundException("");
        return new ResponseEntity<>(UserTennisDto.from(userTennis), HttpStatus.OK);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<UserTennisDto> getUserTennisByUsername(String username) {
        UserTennis userTennis = userTennisService.getUserTennisByUsername(username);
        if (userTennis == null)
            throw new UserTennisNotFoundException(username);
        return new ResponseEntity<>(UserTennisDto.from(userTennis), HttpStatus.OK);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<UserTennisDto> getUserTennisByEmail(@PathVariable final String email) {
        UserTennis userTennis = userTennisService.getUserTennisByEmail(email);
        if (userTennis == null) {
            throw new UserTennisNotFoundException(email);
        }
        return ResponseEntity.ok(UserTennisDto.from(userTennis));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserTennisDto>> getUsersTennis() {
        List<UserTennis> userstennis = userTennisService.getUsersTennis();
        List<UserTennisDto> userTennisDto = userstennis.stream().map(UserTennisDto::from).toList();
        return new ResponseEntity<>(userTennisDto, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserTennisDto> editUserTennis(
            @PathVariable final Long id,
            @RequestBody final UserTennisDto userTennisDto) {
        UserTennis userTennis = userTennisService.editUserTennis(id, UserTennis.from(userTennisDto));
        return new ResponseEntity<>(UserTennisDto.from(userTennis), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserTennisDto> deleteUserTennis(@PathVariable final Long id) {
        UserTennis userTennis = userTennisService.deleteUserTennis(id);
        return ResponseEntity.ok(UserTennisDto.from(userTennis));
    }

    @PostMapping("/user")
    public ResponseEntity<UserTennisDto> addUserTennis(@RequestBody final UserTennisDto userTennisDto) {

        UserTennis userTennis = userTennisService.addUserTennis(UserTennis.from(userTennisDto));
        return new ResponseEntity<>(UserTennisDto.from(userTennis), HttpStatus.CREATED);
    }

    @PostMapping("/user/{idUserTennis}/role/{idRole}/add")
    public ResponseEntity<UserTennisDto> addRoleToUserTennis(
            @PathVariable final Long idUserTennis,
            @PathVariable final Long idRole) {
        UserTennis userTennis = userTennisService.addRoleToUserTennis(idUserTennis, idRole);
        return new ResponseEntity<>(UserTennisDto.from(userTennis), HttpStatus.OK);
    }

    @DeleteMapping("/user/{idUserTennis}/role/{idRole}/delete")
    public ResponseEntity<UserTennisDto> deleteRoleFromUserTennis(
            @PathVariable final Long idUserTennis,
            @PathVariable final Long idRole) {
        UserTennis userTennis = userTennisService.removeRoleFromUserTennis(idUserTennis, idRole);
        return new ResponseEntity<>(UserTennisDto.from(userTennis), HttpStatus.OK);
    }

}
