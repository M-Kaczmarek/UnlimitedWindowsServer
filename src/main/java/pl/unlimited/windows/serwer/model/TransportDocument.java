package pl.unlimited.windows.serwer.model;

import pl.unlimited.windows.serwer.model.dto.PackingDocumentDto;
import pl.unlimited.windows.serwer.model.dto.TransportDocumentDto;

import javax.persistence.*;

@Entity
public class TransportDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_seq")
    @SequenceGenerator(name = "transport_seq", sequenceName = "SEQ_TRANSPORT_60", allocationSize = 1)
    private Long id;

    private String generatedPackageNumber;

    @OneToOne
    private PackingDocument packingDocument;

    public TransportDocument() {
    }

    public TransportDocument(Long id, String generatedPackageNumber, PackingDocument packingDocument) {
        this.id = id;
        this.generatedPackageNumber = generatedPackageNumber;
        this.packingDocument = packingDocument;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeneratedPackageNumber() {
        return generatedPackageNumber;
    }

    public void setGeneratedPackageNumber(String generatedPackageNumber) {
        this.generatedPackageNumber = generatedPackageNumber;
    }

    public PackingDocument getPackingDocument() {
        return packingDocument;
    }

    public void setPackingDocument(PackingDocument packingDocument) {
        this.packingDocument = packingDocument;
    }

    public TransportDocumentDto toDto (){
        return new TransportDocumentDto.TransportDocumentDtoBuilder()
                .id(id)
                .generatedPackageNumber(generatedPackageNumber)
                .packingDocumentDto(packingDocument.toDto())
                .build();
    }
}
