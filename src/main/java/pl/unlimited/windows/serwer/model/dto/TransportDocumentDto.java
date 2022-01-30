package pl.unlimited.windows.serwer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = TransportDocumentDto.TransportDocumentDtoBuilder.class)
public class TransportDocumentDto {
    private final Long id;
    private final String generatedPackageNumber;
    private final PackingDocumentDto packingDocumentDto;

    private TransportDocumentDto(TransportDocumentDtoBuilder builder) {
        this.id = builder.id;
        this.generatedPackageNumber = builder.generatedPackageNumber;
        this.packingDocumentDto = builder.packingDocumentDto;
    }

    public Long getId() {
        return id;
    }

    public String getGeneratedPackageNumber() {
        return generatedPackageNumber;
    }

    public PackingDocumentDto getPackingDocumentDto() {
        return packingDocumentDto;
    }

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class TransportDocumentDtoBuilder{
        private Long id;
        private String generatedPackageNumber;
        private PackingDocumentDto packingDocumentDto;

        public TransportDocumentDto.TransportDocumentDtoBuilder id(final Long id){
            this.id = id;
            return this;
        }
        public TransportDocumentDto.TransportDocumentDtoBuilder generatedPackageNumber(final String generatedPackageNumber){
            this.generatedPackageNumber = generatedPackageNumber;
            return this;
        }
        public TransportDocumentDto.TransportDocumentDtoBuilder packingDocumentDto(final PackingDocumentDto packingDocumentDto){
            this.packingDocumentDto = packingDocumentDto;
            return this;
        }
        public TransportDocumentDto build(){
            return new TransportDocumentDto(this);
        }
    }
}
