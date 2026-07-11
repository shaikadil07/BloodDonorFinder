package blooddonor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class BloodRequestController {

    @Autowired
    private BloodRequestRepository repository;

    @GetMapping("/api/requests")
    public List<BloodRequest> getAllRequests() {
        return repository.findAll();
    }

    @PostMapping("/api/requests")
    public BloodRequest addRequest(
            @RequestBody BloodRequest request) {

        return repository.save(request);
    }

    @GetMapping("/api/myrequests/{email}")
    public List<BloodRequest> getMyRequests(
            @PathVariable String email) {

        return repository.findByEmail(email);
    }
}