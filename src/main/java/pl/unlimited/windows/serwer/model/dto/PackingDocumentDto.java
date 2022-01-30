package pl.unlimited.windows.serwer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = PackingDocumentDto.PackingDocumentDtoBuilder.class)
public class PackingDocumentDto {
    private final Long id;
    private final Boolean isPacked;
    private final ProductionDocumentDto productionDocumentDto;

    public PackingDocumentDto(PackingDocumentDtoBuilder builder) {
        this.id = builder.id;
        this.isPacked = builder.isPacked;
        this.productionDocumentDto = builder.productionDocumentDto;
    }

    public Long getId() {
        return id;
    }

    public Boolean getPacked() {
        return isPacked;
    }

    public ProductionDocumentDto getProductionDocumentDto() {
        return productionDocumentDto;
    }

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class PackingDocumentDtoBuilder{
        private Long id;
        private Boolean isPacked;
        private ProductionDocumentDto productionDocumentDto;

        public PackingDocumentDtoBuilder id(final Long id){
            this.id = id;
            return this;
        }
        public PackingDocumentDtoBuilder isPacked(final Boolean isPacked){
            this.isPacked = isPacked;
            return this;
        }
        public PackingDocumentDtoBuilder productionDocumentDto(final ProductionDocumentDto productionDocumentDto){
            this.productionDocumentDto = productionDocumentDto;
            return this;
        }
        public PackingDocumentDto build(){
            return new PackingDocumentDto(this);
        }
    }
}
