package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.ProductionDocumentApi;
import pl.unlimited.windows.serwer.couriers.api.dpd.AuthData;
import pl.unlimited.windows.serwer.model.ProductionSteps;
import pl.unlimited.windows.serwer.model.dto.ProductionDocumentDto;
import pl.unlimited.windows.serwer.service.production.document.ProductionDocumentService;

import java.util.List;

@RestController
@SecurityRequirement(name = "tokenJWT")
public class ProductionDocumentController implements ProductionDocumentApi {
    private final ProductionDocumentService productionDocumentService;

    public ProductionDocumentController(ProductionDocumentService productionDocumentService) {
        this.productionDocumentService = productionDocumentService;
    }

    @Override
    public ResponseEntity<List<ProductionDocumentDto>> getProductionDocument() {
        return ResponseEntity.ok(productionDocumentService.getProductionDocuments());
    }

    @Override
    public ResponseEntity<ProductionDocumentDto> getProductionDocumentById(Long id) {
        return ResponseEntity.ok(productionDocumentService.getProductionDocumentById(id));
    }
//
//    @Override
//    public ResponseEntity<List<ProductionDocumentDto>> getProductionDocumentByDate(String date) {
//        return ResponseEntity.ok(productionDocumentService.getProductionDocumentsByDate(date));
//    }
//
//    @Override
//    public ResponseEntity<List<ProductionDocumentDto>> getProductionDocumentByStep(String step) {
//        return ResponseEntity.ok(productionDocumentService.getProductionDocumentsBySteps(step));
//    }

    @Override
    public ResponseEntity<List<ProductionDocumentDto>> getProductionDocumentByDateAndStep(String date, String dateFrom,String dateTo,ProductionSteps step) {
        return ResponseEntity.ok(productionDocumentService.getProductionDocumentsByDateAndStep(date,dateFrom,dateTo, step));
    }

    @Override
    public ResponseEntity<List<ProductionDocumentDto>> doProductionStep(ProductionSteps nextStep, Long id) {
        return ResponseEntity.ok(productionDocumentService.doNextStep(nextStep, id));
    }
}
