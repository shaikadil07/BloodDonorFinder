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

        System.out.println("========== SIGNUP REQUEST ==========");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());

        User savedUser = repository.saveAndFlush(user);

        System.out.println("Saved User ID: " + savedUser.getId());
        System.out.println("Total Users: " + repository.count());

        return savedUser;
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

        System.out.println("========== USERS API ==========");

        java.util.List users = repository.findAll();

        System.out.println("Total Users = " + users.size());

        return users;
    }

    @GetMapping("/api/test")
    public String test() {
        return "User Controller Working";
    }

    @GetMapping("/api/usercount")
    public String userCount() {
        return "Users = " + repository.count();
    }
}