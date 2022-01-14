package btlhttt.repositories;

import btlhttt.models.ComputerError_Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComputerErrorMarkRepository extends JpaRepository<ComputerError_Mark, String> {
    Optional<ComputerError_Mark> findByUuid(String uuid);
    List<ComputerError_Mark> findByComputerMarkUuid(String uuid);
}
