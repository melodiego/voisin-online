package br.com.voisinonline.config.security.roles;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private  FirebaseAuth firebaseAuth;

    @IsSuper
    @PutMapping("role/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addRole(@RequestParam String uid, @RequestParam String role) throws Exception {
        roleService.addRole(uid, role);
    }

    @IsSuper
    @DeleteMapping("role/remove")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeRole(@RequestParam String uid, @RequestParam String role) {
        roleService.removeRole(uid, role);
    }
}
