package blooddonor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloodRequestRepository
        extends JpaRepository<BloodRequest, Long> {

    List<BloodRequest> findByEmail(String email);

}