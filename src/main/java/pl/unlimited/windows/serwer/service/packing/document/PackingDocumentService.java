package pl.unlimited.windows.serwer.service.packing.document;

import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.error.exception.ErrorCode;
import pl.unlimited.windows.serwer.error.exception.ProductionDocumentNotFoundException;
import pl.unlimited.windows.serwer.model.PackingDocument;
import pl.unlimited.windows.serwer.model.TransportDocument;
import pl.unlimited.windows.serwer.model.dto.PackingDocumentDto;
import pl.unlimited.windows.serwer.repository.PackingDocumentRepository;
import pl.unlimited.windows.serwer.repository.ProductionDocumentRepository;
import pl.unlimited.windows.serwer.repository.TransportDocumentRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PackingDocumentService {

    private final PackingDocumentRepository packingDocumentRepository;
    private final ProductionDocumentRepository productionDocumentRepository;
    private final TransportDocumentRepository transportDocumentRepository;

    public PackingDocumentService(PackingDocumentRepository packingDocumentRepository, ProductionDocumentRepository productionDocumentRepository, TransportDocumentRepository transportDocumentRepository) {
        this.packingDocumentRepository = packingDocumentRepository;
        this.productionDocumentRepository = productionDocumentRepository;
        this.transportDocumentRepository = transportDocumentRepository;
    }

    public List<PackingDocumentDto> getPackingDocument() {
        return packingDocumentRepository.findAll()
                .stream()
                .filter(packingDocument -> Boolean.FALSE.equals(packingDocument.isPacked()))
                .map(PackingDocument::toDto)
                .collect(Collectors.toList());
    }

    public PackingDocumentDto packedProduct (final Long id){
       var packingDocument = packingDocumentRepository.findById(id).orElseThrow(() -> new ProductionDocumentNotFoundException("Packing document is with id: " + id + " not found", ErrorCode.PACKING_DOCUMENT_NOT_FOUND));

       packingDocument.setPacked(Boolean.TRUE);
     var savedPackingDocument = packingDocumentRepository.save(packingDocument);

      var transportDocument =  new TransportDocument();
      transportDocument.setPackingDocument(savedPackingDocument);
        transportDocumentRepository.save(transportDocument);
       return packingDocument.toDto();
    }

    public List<PackingDocumentDto> getAllPackingDocuments() {
        return packingDocumentRepository.findAll()
                .stream()
                .map(PackingDocument::toDto)
                .collect(Collectors.toList());
    }
}