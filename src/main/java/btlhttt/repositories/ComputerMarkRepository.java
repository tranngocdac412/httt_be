package btlhttt.repositories;

import btlhttt.models.ComputerMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComputerMarkRepository extends JpaRepository<ComputerMark, String> {
    Optional<ComputerMark> findByUuid(String uuid);
    List<ComputerMark> findByDescription(String description);
}
