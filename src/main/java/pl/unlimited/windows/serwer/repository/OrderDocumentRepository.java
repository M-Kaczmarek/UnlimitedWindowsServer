package pl.unlimited.windows.serwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.unlimited.windows.serwer.model.OrderDocument;

@Repository
public interface OrderDocumentRepository extends JpaRepository<OrderDocument, Long> {
}
