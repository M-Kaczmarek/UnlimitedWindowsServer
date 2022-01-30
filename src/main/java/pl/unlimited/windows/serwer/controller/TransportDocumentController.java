package pl.unlimited.windows.serwer.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.unlimited.windows.serwer.api.TransportDocumentApi;
import pl.unlimited.windows.serwer.model.dto.TransportDocumentDto;
import pl.unlimited.windows.serwer.service.transport.document.TransportDocumentService;

import java.util.List;

@RestController
@SecurityRequirement(name = "tokenJWT")
public class TransportDocumentController implements TransportDocumentApi{

    private TransportDocumentService transportDocumentService;

    public TransportDocumentController(TransportDocumentService transportDocumentService) {
        this.transportDocumentService = transportDocumentService;
    }

    @Override
    public ResponseEntity<List<TransportDocumentDto>> getAllTransportDocuments() {
        return ResponseEntity.ok(transportDocumentService.getAllTransportDocuments());
    }
}
