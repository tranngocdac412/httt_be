package btlhttt.repositories;

import btlhttt.models.ComputerError;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComputerErrorRepository extends JpaRepository<ComputerError, String> {
    Optional<ComputerError> findByUuid(String uuid);
    List<ComputerError> findByDescription(String description);
}
