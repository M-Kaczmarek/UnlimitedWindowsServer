package pl.unlimited.windows.serwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.unlimited.windows.serwer.model.PackingDocument;

@Repository
public interface PackingDocumentRepository extends JpaRepository<PackingDocument, Long> {
}
