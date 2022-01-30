package pl.unlimited.windows.serwer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import pl.unlimited.windows.serwer.model.ProductionSteps;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@JsonDeserialize(builder = ProductionDocumentDto.ProductionDocumentDtoBuilder.class)
public class ProductionDocumentDto {
    private final Long id;
    private final ProductionSteps productionSteps;
    private final OrderDocumentDto orderDocumentDto;
    private final LocalDate createDate;
    private final LocalDate predictionEndDate;
    private final ProductionSteps previousProductionSteps;
    private final ProductionSteps nextProductionSteps;

    public Long getId() {
        return id;
    }

    public ProductionSteps getProductionSteps() {
        return productionSteps;
    }

    public OrderDocumentDto getOrderDocumentDto() {
        return orderDocumentDto;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getPredictionEndDate() {
        return predictionEndDate;
    }

    public ProductionSteps getPreviousProductionSteps() {
        return previousProductionSteps;
    }

    public ProductionSteps getNextProductionSteps() {
        return nextProductionSteps;
    }

    private ProductionDocumentDto(ProductionDocumentDtoBuilder builder) {
        this.id = builder.id;
        this.productionSteps = builder.productionSteps;
        this.orderDocumentDto = builder.orderDocumentDto;
        this.createDate = builder.createDate;
        this.predictionEndDate = builder.predictionEndDate;
        this.nextProductionSteps = builder.nextProductionSteps;
        this.previousProductionSteps = builder.previousProductionSteps;
    }

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class ProductionDocumentDtoBuilder{
        private Long id;
        private  ProductionSteps productionSteps;
        private  OrderDocumentDto orderDocumentDto;
        private  LocalDate createDate;
        private  LocalDate predictionEndDate;
        private  ProductionSteps previousProductionSteps;
        private  ProductionSteps nextProductionSteps;

        public ProductionDocumentDtoBuilder id(final Long id){
        this.id = id;

        return this;
        }
        public ProductionDocumentDtoBuilder createDate(final LocalDate createDate){
            this.createDate = createDate;
            return this;
        }
        public ProductionDocumentDtoBuilder predictionEndDate(final LocalDate predictionEndDate){
            this.predictionEndDate = predictionEndDate;
            return this;
        }
        public ProductionDocumentDtoBuilder previousProductionSteps(final ProductionSteps previousProductionSteps){
            this.previousProductionSteps = previousProductionSteps;
            return this;
        }
        public ProductionDocumentDtoBuilder nextProductionSteps(final ProductionSteps nextProductionSteps){
            this.nextProductionSteps = nextProductionSteps;
            return this;
        }

        public ProductionDocumentDtoBuilder productionSteps(final ProductionSteps productionSteps){
            this.productionSteps = productionSteps;
            return this;
        }

        public ProductionDocumentDtoBuilder orderDocumentDto(final OrderDocumentDto orderDocumentDto){
            this.orderDocumentDto = orderDocumentDto;
            return this;
        }
        public ProductionDocumentDto build(){
            return new ProductionDocumentDto(this);
        }

    }
}
