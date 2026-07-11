package blooddonor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @GetMapping("/api/donors")
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @GetMapping("/api/donors/{bloodGroup}")
    public List<Donor> getDonorsByBloodGroup(
            @PathVariable String bloodGroup) {

        return donorRepository.findByBloodGroup(bloodGroup);
    }

    @GetMapping("/api/donors/{bloodGroup}/{city}")
    public List<Donor> getDonorsByBloodGroupAndCity(
            @PathVariable String bloodGroup,
            @PathVariable String city) {

        return donorRepository.findByBloodGroupAndCity(
                bloodGroup,
                city);
    }

    @PostMapping("/api/donors")
    public Donor addDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }
    @GetMapping("/api/mydonations/{email}")
    public List<Donor> getMyDonations(
        @PathVariable String email) {

    return donorRepository.findByEmail(email);

}
}