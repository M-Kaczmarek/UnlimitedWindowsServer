package pl.unlimited.windows.serwer.model;

import org.aspectj.lang.annotation.Before;
import pl.unlimited.windows.serwer.model.dto.OrderDocumentDto;
import pl.unlimited.windows.serwer.model.dto.ProductionDocumentDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProductionDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "production_seq")
    @SequenceGenerator(name = "production_seq", sequenceName = "SEQ_PRODUCTION_25", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductionSteps productionSteps;
    private LocalDate createDate;
    private LocalDate predictionEndDate;
    @Enumerated(EnumType.STRING)
    private ProductionSteps previousProductionSteps;
    @Enumerated(EnumType.STRING)
    private ProductionSteps nextProductionSteps;


    @OneToOne
    private OrderDocument orderDocument;


    public ProductionDocument(Long id, ProductionSteps productionSteps, LocalDate createDate, LocalDate predictionEndDate, ProductionSteps previousProductionSteps, ProductionSteps nextProductionSteps, OrderDocument orderDocument) {
        this.id = id;
        this.productionSteps = productionSteps;
        this.createDate = createDate;
        this.predictionEndDate = predictionEndDate;
        this.previousProductionSteps = previousProductionSteps;
        this.nextProductionSteps = nextProductionSteps;
        this.orderDocument = orderDocument;
    }

    public ProductionDocument(ProductionSteps productionSteps, OrderDocument orderDocument) {
        this.productionSteps = productionSteps;
        this.orderDocument = orderDocument;
        this.nextProductionSteps = ProductionSteps.STEEL_CUTTING;
    }

    public ProductionDocument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductionSteps getProductionSteps() {
        return productionSteps;
    }

    public void setProductionSteps(ProductionSteps productionSteps) {
        this.productionSteps = productionSteps;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getPredictionEndDate() {
        return predictionEndDate;
    }

    public void setPredictionEndDate(LocalDate predictionEndDate) {
        this.predictionEndDate = predictionEndDate;
    }

    public ProductionSteps getPreviousProductionSteps() {
        return previousProductionSteps;
    }

    public void setPreviousProductionSteps(ProductionSteps previousProductionSteps) {
        this.previousProductionSteps = previousProductionSteps;
    }

    public ProductionSteps getNextProductionSteps() {
        return nextProductionSteps;
    }

    public void setNextProductionSteps(ProductionSteps nextProductionSteps) {
        this.nextProductionSteps = nextProductionSteps;
    }

    public OrderDocument getOrderDocument() {
        return orderDocument;
    }

    public void setOrderDocument(OrderDocument orderDocument) {
        this.orderDocument = orderDocument;
    }

    @PrePersist
    public void createData(){
        this.createDate = LocalDate.now();
        this.predictionEndDate = this.createDate.plusDays(7);
    }

    public ProductionDocumentDto toDto(){
        return new ProductionDocumentDto.ProductionDocumentDtoBuilder()
                .id(id)
                .productionSteps(productionSteps)
                .createDate(createDate)
                .predictionEndDate(predictionEndDate)
                .nextProductionSteps(nextProductionSteps)
                .previousProductionSteps(previousProductionSteps)
                .orderDocumentDto(orderDocument.toDto())
                .build();
    }
}
