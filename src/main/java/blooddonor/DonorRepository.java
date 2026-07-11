package blooddonor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonorRepository
        extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroup(String bloodGroup);

    List<Donor> findByBloodGroupAndCity(
            String bloodGroup,
            String city);

    List<Donor> findByEmail(String email);

}