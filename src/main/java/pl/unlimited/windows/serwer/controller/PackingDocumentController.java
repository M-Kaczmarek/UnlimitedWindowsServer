package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.PackingDocumentApi;
import pl.unlimited.windows.serwer.model.dto.PackingDocumentDto;
import pl.unlimited.windows.serwer.service.packing.document.PackingDocumentService;

import java.util.List;

@RestController
@SecurityRequirement(name = "tokenJWT")
public class PackingDocumentController implements PackingDocumentApi {
    private final PackingDocumentService packingDocumentService;

    public PackingDocumentController(PackingDocumentService packingDocumentService) {
        this.packingDocumentService = packingDocumentService;
    }

    @Override
    public ResponseEntity<List<PackingDocumentDto>> getPackingDocument() {
        return ResponseEntity.ok(packingDocumentService.getPackingDocument());
    }

    @Override
    public ResponseEntity<List<PackingDocumentDto>> getPackingAllDocuments() {
        return ResponseEntity.ok(packingDocumentService.getAllPackingDocuments());
    }

    @Override
    public ResponseEntity<PackingDocumentDto> packedProduct(Long id) {
        return ResponseEntity.ok(packingDocumentService.packedProduct(id));
    }
}
