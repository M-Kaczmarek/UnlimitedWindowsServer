package pl.unlimited.windows.serwer.model;

import pl.unlimited.windows.serwer.model.dto.PackingDocumentDto;

import javax.persistence.*;

@Entity
public class PackingDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packing_seq")
    @SequenceGenerator(name = "packing_seq", sequenceName = "SEQ_PACKING_30", allocationSize = 1)
    private Long id;

    private Boolean isPacked = Boolean.FALSE;

    @OneToOne
    private ProductionDocument productionDocument;

    public PackingDocument() {
    }

    public PackingDocument(Long id, Boolean isPacked, ProductionDocument productionDocument) {
        this.id = id;
        this.isPacked = isPacked;
        this.productionDocument = productionDocument;
    }

    public PackingDocument(ProductionDocument productionDocument) {
        this.productionDocument = productionDocument;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isPacked() {
        return isPacked;
    }

    public void setPacked(Boolean packed) {
        isPacked = packed;
    }

    public ProductionDocument getProductionDocument() {
        return productionDocument;
    }

    public void setProductionDocument(ProductionDocument productionDocument) {
        this.productionDocument = productionDocument;
    }

    public PackingDocumentDto toDto (){
        return new PackingDocumentDto.PackingDocumentDtoBuilder()
                .id(this.id)
                .isPacked(this.isPacked)
                .productionDocumentDto(productionDocument.toDto())
                .build();
    }
}
