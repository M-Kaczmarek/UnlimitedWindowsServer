package pl.unlimited.windows.serwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.unlimited.windows.serwer.model.WindowSize;

@Repository
public interface WindowSizeRepository extends JpaRepository<WindowSize, Long> {
}
