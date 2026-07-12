package blooddonor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/api/signup")
    public User signup(@RequestBody User user) {
        return repository.save(user);
    }

   @PostMapping("/api/login")
public ResponseEntity login(
        @RequestBody User user) {

    User foundUser =
            repository.findByEmailAndPassword(
                    user.getEmail(),
                    user.getPassword());

    if(foundUser != null){
        return ResponseEntity.ok(foundUser);
    }

    return ResponseEntity
            .status(401)
            .body("Invalid Email or Password");
}

    @GetMapping("/api/users")
    public java.util.List getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/api/test")
    public String test() {
        return "User Controller Working";
    }
}