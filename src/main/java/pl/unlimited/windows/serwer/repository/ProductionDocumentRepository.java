package pl.unlimited.windows.serwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.unlimited.windows.serwer.model.ProductionDocument;
import pl.unlimited.windows.serwer.model.ProductionSteps;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductionDocumentRepository extends JpaRepository<ProductionDocument, Long> {
    @Query("SELECT pd FROM ProductionDocument pd where pd.createDate = ?1")
    List<ProductionDocument> getProductionDocumentByDate(LocalDate date);

    @Query("SELECT pd FROM ProductionDocument pd where pd.createDate >= ?1 AND pd.createDate <= ?2")
    List<ProductionDocument> getProductionDocumentByDateRange(LocalDate dateFrom, LocalDate dateTo);

    @Query("SELECT pd FROM ProductionDocument pd where pd.createDate = ?1 and pd.productionSteps = ?2")
    List<ProductionDocument> getProductionDocumentByDateAnAndProductionSteps(LocalDate date, ProductionSteps step);

    @Query("SELECT pd FROM ProductionDocument pd where pd.productionSteps = ?1")
    List<ProductionDocument> getProductionDocumentByProductionStep(ProductionSteps step);

    @Query("SELECT pd FROM ProductionDocument pd where pd.productionSteps = ?1 AND pd.createDate >= ?2 AND pd.createDate <= ?3")
    List<ProductionDocument> getProductionDocumentByProductionStepAndDateRange(ProductionSteps step,LocalDate dateFrom, LocalDate dateTo);

    boolean existsProductionDocumentByOrderDocumentId(Long id);

    void deleteProductionDocumentByOrderDocumentId(Long id);
}
