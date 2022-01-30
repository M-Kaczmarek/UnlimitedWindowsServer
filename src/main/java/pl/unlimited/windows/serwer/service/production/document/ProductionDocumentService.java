package pl.unlimited.windows.serwer.service.production.document;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.OrderDocumentNotFoundException;
import pl.unlimited.windows.serwer.error.exception.ProductionDocumentNotFoundException;
import pl.unlimited.windows.serwer.model.OrderDocument;
import pl.unlimited.windows.serwer.model.PackingDocument;
import pl.unlimited.windows.serwer.model.ProductionDocument;
import pl.unlimited.windows.serwer.model.ProductionSteps;
import pl.unlimited.windows.serwer.model.dto.ProductionDocumentDto;
import pl.unlimited.windows.serwer.repository.OrderDocumentRepository;
import pl.unlimited.windows.serwer.repository.PackingDocumentRepository;
import pl.unlimited.windows.serwer.repository.ProductionDocumentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.unlimited.windows.serwer.model.ProductionSteps.PROFILE_CUTTING;
import static pl.unlimited.windows.serwer.model.ProductionSteps.STEEL_CUTTING;

@Service
public class ProductionDocumentService {

    private final ProductionDocumentRepository productionDocumentRepository;
    private final OrderDocumentRepository orderDocumentRepository;
    private final PackingDocumentRepository packingDocumentRepository;

    public ProductionDocumentService(ProductionDocumentRepository productionDocumentRepository, OrderDocumentRepository orderDocumentRepository, PackingDocumentRepository packingDocumentRepository) {
        this.productionDocumentRepository = productionDocumentRepository;
        this.orderDocumentRepository = orderDocumentRepository;
        this.packingDocumentRepository = packingDocumentRepository;
    }

    public List<ProductionDocumentDto> getProductionDocuments(){
        List<ProductionDocument> productionDocuments = productionDocumentRepository.findAll();
        List<OrderDocument> orderDocuments = orderDocumentRepository.findAll();
       List<OrderDocument> orderDocumentsWithProductionDocument = getOrderDocumentsWithProductionDocument(productionDocuments, orderDocuments);
       orderDocuments.removeAll(orderDocumentsWithProductionDocument);
       List<ProductionDocumentDto> productionDocumentDtos = productionDocuments
                .stream()
                .map(ProductionDocument::toDto)
                .collect(Collectors.toList());

        List<ProductionDocumentDto> newProductionDocuments = orderDocuments.stream()
                .map(orderDocument -> productionDocumentRepository.save(new ProductionDocument(PROFILE_CUTTING, orderDocument)))
                .map(ProductionDocument::toDto)
                .collect(Collectors.toList());

        productionDocumentDtos.addAll(newProductionDocuments);

        return productionDocumentDtos.stream().sorted(Comparator.comparing(ProductionDocumentDto::getId)).collect(Collectors.toList());
    }

    private List<OrderDocument> getOrderDocumentsWithProductionDocument(List<ProductionDocument> productionDocuments, List<OrderDocument> orderDocuments) {
        List<OrderDocument> result = new ArrayList<>();
        for(ProductionDocument doc : productionDocuments){
            for(OrderDocument or : orderDocuments) {
            if(or.getId().equals(doc.getOrderDocument().getId())){
                result.add(or);
            }
            }
        }
        return result;
    }

    public List<ProductionDocumentDto> getProductionDocumentsByDate(String date){
        String[] split = date.split("-");
        return productionDocumentRepository.getProductionDocumentByDate(LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]))).stream().map(ProductionDocument::toDto).collect(Collectors.toList());
    }

    public Boolean isProductionDocumentForOrderDocument(final Long id){
       return productionDocumentRepository.existsProductionDocumentByOrderDocumentId(id);
    }

    public void deleteProductionDocumentForOrderDocument(final Long id){
        productionDocumentRepository.deleteProductionDocumentByOrderDocumentId(id);
    }


    public List<ProductionDocumentDto> getProductionDocumentsBySteps(ProductionSteps step) {

        return productionDocumentRepository.getProductionDocumentByProductionStep(step).stream().map(ProductionDocument::toDto).collect(Collectors.toList());
    }

    public List<ProductionDocumentDto> getProductionDocumentsByDateAndStep(String date, String dateFrom,String dateTo,ProductionSteps step) {
        if (isFilterByDate(date, dateFrom, dateTo, step)) {
            return getProductionDocumentsByDate(date).stream().sorted(Comparator.comparing(ProductionDocumentDto::getId)).collect(Collectors.toList());
        } else if (isFilterByStep(date, dateFrom, dateTo, step)) {
            return getProductionDocumentsBySteps(step).stream().sorted(Comparator.comparing(ProductionDocumentDto::getId)).collect(Collectors.toList());
        } else if(isFilterByDateRange(date, dateFrom, dateTo, step)){
           return productionDocumentRepository.getProductionDocumentByDateRange(prepareLocalDate(dateFrom), prepareLocalDate(dateTo)).stream().map(ProductionDocument::toDto).sorted(Comparator.comparing(ProductionDocumentDto::getId)).collect(Collectors.toList());
        }
        else if(isFilterByDateAndStep(date, dateFrom, dateTo, step)){
            return productionDocumentRepository.getProductionDocumentByDateAnAndProductionSteps(prepareLocalDate(date), step).stream().map(ProductionDocument::toDto).sorted(Comparator.comparing(ProductionDocumentDto::getId)).collect(Collectors.toList());
        } else if(isFilterByDateRangeAndStep(date, dateFrom, dateTo, step)){
            return productionDocumentRepository.getProductionDocumentByProductionStepAndDateRange(step, prepareLocalDate(dateFrom), prepareLocalDate(dateTo))
                    .stream()
                    .map(ProductionDocument::toDto)
                    .sorted(Comparator.comparing(ProductionDocumentDto::getId))
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException();
    }

    private LocalDate prepareLocalDate(final  String date){
        String[] split = date.split("-");

        return LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }
   private Boolean isFilterByStep(String date, String dateFrom, String dateTo, ProductionSteps step){
        return step != null && date == null  && dateFrom == null  && dateTo == null;
   }
    private Boolean isFilterByDate(String date, String dateFrom, String dateTo, ProductionSteps step){
        return step == null && date != null  && dateFrom == null  && dateTo == null;
    }
    private Boolean isFilterByDateAndStep(String date, String dateFrom, String dateTo, ProductionSteps step){
        return step != null && date != null  && dateFrom == null  && dateTo == null;
    }
    private Boolean isFilterByDateRange(String date, String dateFrom, String dateTo, ProductionSteps step){
        return step == null && date == null  && dateFrom != null  && dateTo != null;
    }
    private Boolean isFilterByDateRangeAndStep(String date, String dateFrom, String dateTo, ProductionSteps step){
        return step != null && date == null  && dateFrom != null  && dateTo != null;
    }
    public List<ProductionDocumentDto> doNextStep(ProductionSteps newProductionStep, Long id) {
       var productionDocument = productionDocumentRepository.findById(id).orElseThrow(() -> new ProductionDocumentNotFoundException("Production document is with id: " + id + " not found", ErrorCode.PRODUCTION_DOCUMENT_NOT_FOUND));
var nextProductionStep = productionDocument.getNextProductionSteps();
           productionDocument.setPreviousProductionSteps(productionDocument.getProductionSteps());
           productionDocument.setProductionSteps(productionDocument.getNextProductionSteps());
           productionDocument.setNextProductionSteps(productionDocument.getNextProductionSteps() == null || productionDocument.getNextProductionSteps() == ProductionSteps.PACKING  ? ProductionSteps.PACKING :ProductionSteps.valueOf(getNextProductionStep(productionDocument.getNextProductionSteps())));
           productionDocumentRepository.save(productionDocument);
           if(ProductionSteps.PACKING.equals(nextProductionStep)){
               packingDocumentRepository.save(new PackingDocument(productionDocument));
           }
           productionDocumentRepository.flush();

       return productionDocumentRepository.findAll().stream().map(ProductionDocument::toDto).sorted(Comparator.comparing(ProductionDocumentDto::getId)).collect(Collectors.toList());
    }

    private String getNextProductionStep(ProductionSteps newProductionStep) {
        switch (newProductionStep) {
            case PROFILE_CUTTING:
                return "STEEL_CUTTING";
            case STEEL_CUTTING:
                return "FRAME_WELDING";
            case FRAME_WELDING:
                return "SASH_WELDING";
            case SASH_WELDING:
                return "FRAME_FITTING";
            case FRAME_FITTING:
                return "SASH_FITTING";
            case SASH_FITTING:
                return "GLAZING";
            case GLAZING:
                return "ASSEMBLY";
            case ASSEMBLY:
                return "QUALITY_CONTROL";
            case QUALITY_CONTROL:
                return "PACKING";
            default:
                throw new IllegalArgumentException();
        }
    }

    public ProductionDocumentDto getProductionDocumentById(Long id) {
        return productionDocumentRepository.findById(id).map(ProductionDocument::toDto).orElseThrow(() -> new ProductionDocumentNotFoundException("Production document is with id: " + id + " not found", ErrorCode.PRODUCTION_DOCUMENT_NOT_FOUND));
    }
}
