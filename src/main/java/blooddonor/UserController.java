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
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            if (user.getName() == null || user.getName().isBlank()
                    || user.getEmail() == null || user.getEmail().isBlank()
                    || user.getPassword() == null || user.getPassword().isBlank()) {
                return ResponseEntity.badRequest().body("Name, email, and password are required");
            }

            if (repository.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.status(409).body("Email already registered");
            }

            User savedUser = repository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }

   @PostMapping("/api/login")
public ResponseEntity<?> login(
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